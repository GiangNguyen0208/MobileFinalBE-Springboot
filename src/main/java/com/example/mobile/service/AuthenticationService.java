package com.example.mobile.service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

import com.example.mobile.entity.Role;
import com.example.mobile.entity.Shop;
import com.example.mobile.entity.User;
import com.example.mobile.repository.RoleRepository;
import com.example.mobile.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.mobile.dto.request.AuthenticationReq;
import com.example.mobile.dto.request.IntrospectReq;
import com.example.mobile.dto.response.AuthenticationRes;
import com.example.mobile.dto.response.IntrospectRes;
import com.example.mobile.exception.AddException;
import com.example.mobile.exception.ErrorCode;
import com.example.mobile.repository.UserRepository;
import com.example.mobile.service.imp.IAuthentication;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class AuthenticationService implements IAuthentication {
    RoleRepository roleRepository;
    UserRepository userRepository;
    ShopRepository shopRepository;
    @NonFinal
    @Value("${jwt.signerKey}")
    private String SIGNER_KEY;

    @Override
    public AuthenticationRes authentication(AuthenticationReq req) {
        var user = userRepository.findByUsername(req.getUsername().toLowerCase())
                .orElseThrow(() -> new AddException(ErrorCode.USER_NOT_EXISTED));
        List<Shop> shop = shopRepository.findOpenShopsByUserId(user.getId());
        PasswordEncoder passwordEncode = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncode.matches(req.getPassword().toLowerCase(), user.getPassword());

        if (!authenticated)
            throw new AddException(ErrorCode.UNAUTHENTICATED);

        var token = generateToken(user);
        var role = getRoleUser(user);

        return AuthenticationRes.builder()
                .userId(user.getId())
                .token(token)
                .authenticated(true)
                .clientType(role)
                .shopId(shop.getFirst().getId())
                .build();
    }

    private String getRoleUser(User user) {
        return user.getRole().getName();
    }

    @Override
    public IntrospectRes introspect(IntrospectReq req) throws JOSEException, ParseException {
        var token = req.getToken();

        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());  // Nimbus provide JWSVerifier class.

        SignedJWT signedJWT = SignedJWT.parse(token); // This is an object.

//      signedJWT.verify(verifier); // return true or false (true if token content not change).
        Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        return IntrospectRes.builder()
                .valid(verified)
                .build();

    }

    @Override
    public String generateToken(User user) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        Date expirationTime = new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli());     // Time Expiration in 1 hour start now.

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("devteria.com") // Confirm token was issue from ???
                .issueTime(new Date())
                .expirationTime(expirationTime)
                .claim("scope", buildScope(user))
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

    private String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (user.getRole() != null) {
            stringJoiner.add(user.getRole().getName());
        } else {
            throw  new RuntimeException("ROLE IS NOT VALID");
        }
        
        return stringJoiner.toString();
    }
}
