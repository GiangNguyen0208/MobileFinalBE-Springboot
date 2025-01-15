package com.example.mobile.service;

import com.example.mobile.constant.FoodStatus;
import com.example.mobile.dto.request.ProductCreationReq;
import com.example.mobile.dto.request.ProductUpdateReq;
import com.example.mobile.dto.response.CategoryResponse;
import com.example.mobile.dto.response.ImageProductResponse;
import com.example.mobile.dto.response.ProductResponse;
import com.example.mobile.dto.response.ProductWithShop;
import com.example.mobile.entity.Category;
import com.example.mobile.entity.ImageProduct;
import com.example.mobile.entity.Product;
import com.example.mobile.entity.Shop;
import com.example.mobile.exception.AddException;
import com.example.mobile.exception.ErrorCode;
import com.example.mobile.mapper.IProductMapper;
import com.example.mobile.repository.CategoryRepository;
import com.example.mobile.repository.ImageProductRepository;
import com.example.mobile.repository.ProductRepository;
import com.example.mobile.repository.ShopRepository;
import com.example.mobile.service.imp.IImageProduct;
import com.example.mobile.service.imp.IProduct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductService implements IProduct {
    private final ShopRepository shopRepository;
    private final ImageProductRepository imageProductRepository;
    ProductRepository productRepository;
    IProductMapper productMapper;
    IImageProduct imageProductService;
    CategoryRepository categoryRepository;

    @Override
    public boolean addProduct(ProductCreationReq req) {
        try {
            if (productRepository.existsByName(req.getName())) {
                return false;  // Trả về false nếu sản phẩm đã tồn tại
            }

            Category category = categoryRepository.findByName(req.getCategoryName())
                    .orElseThrow(() -> new RuntimeException("Category not found!"));

            Product product = new Product();
            product.setName(req.getName());
            product.setQuantity(req.getQuantity());
            product.setPrice(req.getPrice());
            product.setRating(req.getRating());
            product.setDescription(req.getDescription());
            product.setPosition(req.getPosition());
            product.setCategory(category);
            product.setStatus(FoodStatus.ON_SALE);

            productRepository.save(product);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }




    @Override
    public List<ProductResponse> getListProduct() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        List<ProductResponse> productResponseList = new ArrayList<>();
        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        List<Product> productList = productRepository.findAll();
        for (Product p : productList) {
            List<String> images = new ArrayList<>();
            List<ImageProduct> imageProductList = imageProductRepository.findAllImagesByProductId(p.getId());
            for (ImageProduct image : imageProductList) {
                images.add(image.getLinkImage());
            }
            ProductResponse productResponse = ProductResponse.builder()
                    .id(p.getId())
                    .categoryId(p.getCategory().getId())
                    .name(p.getName())
                    .price(p.getPrice())
                    .position(p.getPosition())
                    .des(p.getDescription())
                    .quantity(p.getQuantity())
                    .rating(p.getRating())
                    .imageLink(images)
                    .build();
            productResponseList.add(productResponse);
        }
        return  productResponseList;
    }

    @Override
    public ProductResponse findProductById(int id) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found!"));
        List<ImageProduct> imageProductList = imageProductRepository.findAllImagesByProductId(id);
        List<String> images = new ArrayList<>();
        for (ImageProduct imageProduct : imageProductList) {
            images.add(imageProduct.getLinkImage());
        }
        return ProductResponse.builder()
                .name(p.getName())
                .price(p.getPrice())
                .des(p.getDescription())
                .quantity(p.getQuantity())
                .categoryName(p.getCategory().getName())
                .id(p.getId())
                .status(p.getStatus())
                .rating(p.getRating())
                .categoryId(p.getCategory().getId())
                .imageLink(images)
                .build();
    }

    @Override
    public ProductResponse productUpdate(int id, ProductUpdateReq req) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found!"));

        // Kiểm tra và chuyển đổi status từ String thành Enum
        FoodStatus status = FoodStatus.valueOf(req.getStatus());

        // Cập nhật giá trị các thuộc tính nếu có thay đổi (bỏ qua null hoặc giá trị không thay đổi)
        if (req.getName() != null && !req.getName().isEmpty()) {
            product.setName(req.getName());
        }
        if (req.getPrice() != 0) { // Kiểm tra giá trị mặc định là 0 (trong trường hợp không có thay đổi)
            product.setPrice(req.getPrice());
        }
        if (req.getCategoryName() != null && !req.getCategoryName().isEmpty()) {
            Category category = categoryRepository.findByName(req.getCategoryName())
                    .orElseThrow(() -> new RuntimeException("Category not found!"));
            product.setCategory(category);
        }
        if (req.getDescription() != null) {
            product.setDescription(req.getDescription());
        }
        if (req.getQuantity() != 0) { // Kiểm tra số lượng mặc định là 0 (trong trường hợp không có thay đổi)
            product.setQuantity(req.getQuantity());
        }
        if (req.getStatus() != null) {
            product.setStatus(status);
        }

        // Lưu sản phẩm sau khi cập nhật
        productRepository.save(product);

        // Tạo ProductResponse với các thông tin đã được cập nhật
        ProductResponse productResponse = ProductResponse.builder()
                .id(product.getId())
                .categoryId(product.getCategory().getId())
                .categoryName(product.getCategory().getName())
                .des(product.getDescription())
                .status(status) // Truyền giá trị String của enum
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();

        return productResponse;
    }


    @Override
    public void deleteProduct(int id) {
        List<ImageProduct> imageProductList = imageProductRepository.findAllImagesByProductId(id);
        for (ImageProduct imageProduct : imageProductList) {
            imageProductRepository.deleteById(imageProduct.getId());
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductResponse> getListProductByCategory(int id) {
        List<ProductResponse> productResponseList = new ArrayList<>();
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found!"));

        List<Product> productList = productRepository.findAllByCategoryAndDeletedFalse(category);
        for (Product product : productList) {
            List<ImageProduct> imageProductList = imageProductRepository.findAllImagesByProductId(product.getId());
            List<String> images = new ArrayList<>();
            for (ImageProduct imageProduct : imageProductList) {
                images.add(imageProduct.getLinkImage());
            }
            ProductResponse productResponse = ProductResponse.builder()
                    .name(product.getName())
                    .price(product.getPrice())
                    .des(product.getDescription())
                    .quantity(product.getQuantity())
                    .rating(product.getRating())
                    .status(product.getStatus())
                    .imageLink(images)
                    .build();
            productResponseList.add(productResponse);
        }
        return productResponseList;
    }

    public List<ProductResponse> getListProductByShopID(int shopId) {
        List<Product> products = productRepository.findByShopId(shopId);

        return products.stream().map(product -> {
            String categoryName = product.getCategory() != null ? product.getCategory().getName() : "Unknown";
            Integer categoryId = product.getCategory() != null ? product.getCategory().getId() : null;

            // Lấy ảnh đầu tiên hoặc ảnh mặc định nếu không có ảnh
            List<ImageProduct> imageLink = imageProductRepository.findAllImagesByProductId(product.getId());
            List<String> images = new ArrayList<>();
            for (ImageProduct imageProduct : imageLink) {
                images.add(imageProduct.getLinkImage());
            }
            // Xây dựng ProductResponse
            return ProductResponse.builder()
                    .id(product.getId())
                    .categoryName(categoryName)
                    .name(product.getName())
                    .categoryId(categoryId)
                    .price(product.getPrice())
                    .des(product.getDescription())
                    .rating(product.getRating())
                    .quantity(product.getQuantity())
                    .imageLink(images)
                    .status(FoodStatus.ON_SALE)
                    .build();
        }).collect(Collectors.toList());
    }




    @Override
    public ProductResponse findProductByName(String name) {
        Product product = productRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Product not found!"));
        ProductResponse productResponse = ProductResponse.builder()
                .name(product.getName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .rating(product.getRating())
                .des(product.getDescription())
                .categoryId(product.getCategory().getId())
                .build();
        return productResponse;
    }

    @Override
    public List<ProductWithShop> getListByShopName(String shopName) {
        // Lấy kết quả từ repository
        List<Object[]> results = productRepository.findProductsByShopName(shopName);
        List<ProductWithShop> productWithShopList = new ArrayList<>();

        for (Object[] result : results) {
            ProductWithShop productWithShop = getProductWithShop(result);
            productWithShopList.add(productWithShop);
        }
        return productWithShopList;
    }

    @Override
    public List<ProductResponse> findAllByNameContains(String productName) {
        List<ProductResponse> productResponseList = new ArrayList<>();
        List<Product> products = productRepository.findAllByNameContains(productName);

        return products.stream().map(product -> {
            String categoryName = product.getCategory() != null ? product.getCategory().getName() : "Unknown";
            Integer categoryId = product.getCategory() != null ? product.getCategory().getId() : null;

            // Lấy ảnh đầu tiên hoặc ảnh mặc định nếu không có ảnh
            List<ImageProduct> imageLink = imageProductRepository.findAllImagesByProductId(product.getId());
            List<String> images = new ArrayList<>();
            for (ImageProduct imageProduct : imageLink) {
                images.add(imageProduct.getLinkImage());
            }
            // Xây dựng ProductResponse
            return ProductResponse.builder()
                    .id(product.getId())
                    .categoryName(categoryName)
                    .name(product.getName())
                    .categoryId(product.getCategory().getId())
                    .price(product.getPrice())
                    .des(product.getDescription())
                    .rating(product.getRating())
                    .quantity(product.getQuantity())
                    .imageLink(images)
                    .status(FoodStatus.ON_SALE)
                    .build();
        }).collect(Collectors.toList());
    }

    private static ProductWithShop getProductWithShop(Object[] result) {
        String productName = (String) result[0];
        Double productPrice = (Double) result[1]; // product price
        Integer productQuantity = (Integer) result[2]; // product quantity
        String shopNameFromResult = (String) result[3]; // shop name

        ProductWithShop productWithShop = new ProductWithShop();
        productWithShop.setName(productName);
        productWithShop.setPrice(productPrice);
        productWithShop.setQuantity(productQuantity);
        productWithShop.setShopName(shopNameFromResult);
        return productWithShop;
    }


}
