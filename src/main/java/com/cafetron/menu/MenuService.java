package com.cafetron.menu;

import com.cafetron.common.exception.ResourceNotFoundException;
import com.cafetron.menu.dto.MenuItemRequest;
import com.cafetron.menu.dto.MenuItemResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuService {

    private final MenuRepository repository;

    public MenuService(MenuRepository repository) {
        this.repository = repository;
    }

    /** Employee-facing: only items offered and in stock today. */
    @Transactional(readOnly = true)
    public List<MenuItemResponse> getTodaysMenu() {
        return repository.findByAvailableTrueAndStockQuantityGreaterThan(0).stream()
                .map(MenuItemResponse::from)
                .toList();
    }

    /** Counter/Admin-facing: everything, including hidden/out-of-stock items. */
    @Transactional(readOnly = true)
    public List<MenuItemResponse> getAll() {
        return repository.findAll().stream().map(MenuItemResponse::from).toList();
    }

    @Transactional
    public MenuItemResponse create(MenuItemRequest req) {
        MenuItem item = MenuItem.builder()
                .name(req.name())
                .description(req.description())
                .category(req.category())
                .price(req.price())
                .stockQuantity(req.stockQuantity())
                .available(req.available())
                .build();
        return MenuItemResponse.from(repository.save(item));
    }

    @Transactional
    public MenuItemResponse update(Long id, MenuItemRequest req) {
        MenuItem item = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item " + id + " not found"));
        item.setName(req.name());
        item.setDescription(req.description());
        item.setCategory(req.category());
        item.setPrice(req.price());
        item.setStockQuantity(req.stockQuantity());
        item.setAvailable(req.available());
        return MenuItemResponse.from(repository.save(item));
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Menu item " + id + " not found");
        }
        repository.deleteById(id);
    }
}
