package com.example.mobile.service;

import com.example.mobile.dto.request.AuthenticationReq;
import com.example.mobile.dto.request.IntrospectReq;
import com.example.mobile.dto.response.AuthenticationRes;
import com.example.mobile.dto.response.IntrospectRes;
import com.example.mobile.exception.AddException;
import com.example.mobile.exception.ErrorCode;
import com.example.mobile.repository.UserRepository;
import com.example.mobile.service.imp.IAuthentication;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class AuthenticationService implements IAuthentication {
    UserRepository userRepository;
    @NonFinal
    @Value("${jwt.signerKey}")
    private String SIGNER_KEY;
    @Override
    public AuthenticationRes authentication(AuthenticationReq req) {
        var user = userRepository.findByUsername(req.getUsername())
                .orElseThrow(() -> new AddException(ErrorCode.USER_NOT_EXISTED));
        PasswordEncoder passwordEncode = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncode.matches(req.getPassword(), user.getPassword());

        if (!authenticated) throw new AddException(ErrorCode.UNAUTHENTICATED);

        var token = generateToken(req.getUsername());

        return AuthenticationRes.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    @Override
    public IntrospectRes introspect(IntrospectReq req) throws JOSEException, ParseException {
        var token = req.getToken();

        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());  // Nimbus provide JWSVerifier class.

        SignedJWT signedJWT = SignedJWT.parse(token) ; // This is an object.

//      signedJWT.verify(verifier); // return true or false (true if token content not change).

        Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        return IntrospectRes.builder()
                .valid(verified)
                .build();

    }

    @Override
    public String generateToken(String username) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);
        Date expirationTime = new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli());     // Time Expiration in 1 hour start now.

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("devteria.com")   // Confirm token was issue from ???
                .issueTime(new Date())
                .expirationTime(expirationTime)
                .claim("userId", "Custom")
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
//      Generate a signer key on web:  https://generate-random.org/encryption-key-generator
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token.!za");
            throw new RuntimeException(e);
        }
    }
}
