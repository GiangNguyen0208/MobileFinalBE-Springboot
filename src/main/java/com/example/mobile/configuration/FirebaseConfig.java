package com.example.mobile.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;

public class FirebaseConfig {
    public void initializeFirebase() throws Exception {
        FileInputStream serviceAccount =
                new FileInputStream("path/to/your-service-account-key.json");

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket("your-project-id.appspot.com")
                .build();

        FirebaseApp.initializeApp(options);
    }
}
