package com.cafetron.menu.dto;

import com.cafetron.menu.MenuItem;

import java.math.BigDecimal;

/** Outbound view of a menu item. Never expose the entity directly. */
public record MenuItemResponse(
        Long id,
        String name,
        String description,
        String category,
        BigDecimal price,
        int stockQuantity,
        boolean available
) {
    public static MenuItemResponse from(MenuItem item) {
        return new MenuItemResponse(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getCategory(),
                item.getPrice(),
                item.getStockQuantity(),
                item.isAvailable()
        );
    }
}
