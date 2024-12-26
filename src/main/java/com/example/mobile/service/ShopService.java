package com.example.mobile.service;

import com.example.mobile.dto.request.ShopRequest;
import com.example.mobile.entity.Shop;
import com.example.mobile.entity.User;
import com.example.mobile.repository.ShopRepository;
import com.example.mobile.service.imp.IShop;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ShopService implements IShop {
    @Autowired
    private ShopRepository shopRepository;
    private UserService userService;

    @Override
    public Shop addShop(ShopRequest shopRequest) {
        User user = userService.getUserById(shopRequest.getUserId());
        Shop shop = new Shop();
        shop.setName(shopRequest.getName());
        shop.setAddress(shopRequest.getAddress());
        shop.setStatus(shopRequest.getStatus());
        shop.setRating(shopRequest.getRating());
        shop.setUser(user);
        return shopRepository.save(shop);
    }

    @Override
    public Shop updateShop(int id, ShopRequest shopRequest) {
        Shop existingShop = shopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shop not found with id "));

        User user = userService.getUserById(shopRequest.getUserId());
        existingShop.setName(shopRequest.getName());
        existingShop.setAddress(shopRequest.getAddress());
        existingShop.setStatus(shopRequest.getStatus());
        existingShop.setRating(shopRequest.getRating());
        existingShop.setUser(user);
        return shopRepository.save(existingShop);
    }

    @Override
    public void deleteShop(int id) {
        if (!shopRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Shop not found with id " + id);
        }
        shopRepository.deleteById(id);
    }

}
