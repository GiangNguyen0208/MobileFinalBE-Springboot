package com.example.mobile.service;

import com.example.mobile.constant.FoodStatus;
import com.example.mobile.constant.StatusShop;
import com.example.mobile.dto.request.ShopCreationReq;
import com.example.mobile.dto.request.ShopUpdateReq;
import com.example.mobile.dto.response.ShopResponse;
import com.example.mobile.entity.Shop;
import com.example.mobile.exception.AddException;
import com.example.mobile.exception.ErrorCode;
import com.example.mobile.mapper.IShopMapper;
import com.example.mobile.repository.ShopRepository;
import com.example.mobile.service.imp.IShop;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ShopService implements IShop {
    ShopRepository shopRepository;
    IShopMapper shopMapper;

    @Override
    public void createShop(ShopCreationReq req) {
        Shop shop = new Shop();
        shop.setName(req.getName());
        shop.setAddress(req.getAddress());
        shop.setStatus(StatusShop.valueOf(req.getStatus()));
        shop.setImage(req.getImage());
        shopRepository.save(shop); // Lưu vào database
    }

    @Override
    public List<ShopResponse> getListShop() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        return shopRepository.findAllByStatusOpen().stream()
                .map(shopMapper::toShopResponse).toList();
    }

    @Override
    public List<ShopResponse> getListShopClosed() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        return shopRepository.findAllByStatusClose().stream()
                .map(shopMapper::toShopResponse).toList();
    }

    @Override
    public ShopResponse findShopById(int id) {
        return shopMapper.toShopResponse(shopRepository.findById(id).orElseThrow(() -> new RuntimeException("Shop not found!")));

    }

    @Override
    public void shopUpdate(int id, ShopUpdateReq req) {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shop not Found"));
        shop.setName(req.getName());
        shop.setAddress(req.getAddress());
        shop.setStatus(StatusShop.valueOf(req.getStatus()));
        shop.setName(req.getName());
        shop.setAddress(req.getAddress());
        shop.setImage(req.getImage());
        shopRepository.save(shop);
    }

    @Override
    public void shopUpdateOpen(int id, ShopUpdateReq req) {

    }


    @Override
    public void deleteShop(int id) {
        shopRepository.deleteById(id);
    }

    @Override
    public ShopResponse findShopByName(String name) {
            Shop shop = (Shop) shopRepository.findByName(name)
                    .orElseThrow(() -> new RuntimeException("Shop not found!"));
            return shopMapper.toShopResponse(shop);

    }

    @Override
    public List<ShopResponse> findDistinctByCategoryList_Name(String categoryName) {
        return List.of();
    }

    @Override
    public List<Integer> getOpenShopIds() {
        return shopRepository.findOpenShopIds();
    }
}
