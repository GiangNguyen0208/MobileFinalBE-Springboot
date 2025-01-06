package com.example.mobile.service.imp;

import com.example.mobile.dto.response.ImageProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IImageProduct {
    List<String> uploadImage(List<MultipartFile> files, int id) throws IOException;
    byte[] downloadImage(String filename);
    List<ImageProductResponse> showProductImage(int idProduct);
    void remove(int id);
}
