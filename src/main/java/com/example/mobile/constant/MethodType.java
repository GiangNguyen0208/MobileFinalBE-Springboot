package com.example.mobile.constant;

public enum MethodType {
    VNPAY("VNPAY"),
    MOMO("MOMO"),
    CASH("CASH");

    private final String methodType;

    MethodType(String methodType) {
        this.methodType = methodType;
    }
    public String getMethodType() {
        return methodType;
    }
}
