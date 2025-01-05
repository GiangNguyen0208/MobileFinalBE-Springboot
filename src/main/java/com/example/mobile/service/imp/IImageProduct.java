package com.example.mobile.service.imp;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IImageProduct {
    String uploadImage(MultipartFile file, int id) throws IOException;
    byte[] downloadImage(String filename);
    List<byte[]> showProductImage(int idProduct);
    void remove(int id);
}
