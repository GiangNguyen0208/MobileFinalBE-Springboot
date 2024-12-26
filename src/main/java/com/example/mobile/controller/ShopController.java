package com.example.mobile.controller;

import com.example.mobile.dto.request.ShopRequest;
import com.example.mobile.entity.Shop;
import com.example.mobile.service.ShopService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shop")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShopController {
    ShopService shopService;

    @PostMapping("/add-shop")
    public ResponseEntity<Shop> addShop(@RequestBody ShopRequest shopRequest) {
        Shop shop = shopService.addShop(shopRequest);
        return ResponseEntity.ok(shop);
    }
    @PutMapping("/update-information-shop")
    public ResponseEntity<Shop>updateShop (@RequestParam int id, @RequestBody ShopRequest shopRequest){
        Shop shop = shopService.updateShop(id,shopRequest);
        return ResponseEntity.ok(shop);
    }
    @DeleteMapping("/delete-shop")
    public ResponseEntity<String> updateShop (@RequestParam int id){
        shopService.deleteShop(id);
        return ResponseEntity.ok("Shop with id " + id + " has been deleted.");
    }
}
