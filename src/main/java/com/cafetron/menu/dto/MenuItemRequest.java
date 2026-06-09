package com.cafetron.menu.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

/** Inbound payload for creating/updating a menu item (COUNTER/ADMIN). */
public record MenuItemRequest(
        @NotBlank String name,
        String description,
        @NotBlank String category,
        @NotNull @DecimalMin("0.0") BigDecimal price,
        @Min(0) int stockQuantity,
        boolean available
) {}
