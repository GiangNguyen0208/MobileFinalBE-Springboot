package com.example.mobile.service.imp;

import com.example.mobile.dto.response.ImageProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IImageProduct {
    List<String> uploadImage(List<String> files, int id) throws IOException;
//    String downloadImage(String filename);
    List<String> showProductImage(int idProduct);
    void remove(int id);
}
