package com.cafetron.menu;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * MODULE 2 (Menu & Stock) — fully worked reference for the layered pattern.
 * Copy this Entity -> Repository -> Service -> Controller -> DTO shape for
 * your own module.
 */
@Entity
@Table(name = "menu_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    /** Daily stock count. Decremented atomically during order placement (Module 3). */
    @Column(nullable = false)
    private int stockQuantity;

    /** Whether the item is offered today at all (independent of stock). */
    @Column(nullable = false)
    private boolean available;
}
