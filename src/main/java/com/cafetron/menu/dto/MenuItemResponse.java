package com.cafetron.menu.dto;

public record MenuItemResponse(
        Long id,
        String itemName,
        double price,
        int stock,
        String foodType,
        boolean isAvailable,
        Long vendorId,
        String vendorName
) {}
