package com.cafetron.menu;

import com.cafetron.common.ApiResponse;
import com.cafetron.menu.dto.MenuItemRequest;
import com.cafetron.menu.dto.MenuItemResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private final MenuService service;

    public MenuController(MenuService service) {
        this.service = service;
    }

    /** Any authenticated user can see today's orderable menu. */
    @GetMapping("/today")
    public ApiResponse<List<MenuItemResponse>> todaysMenu() {
        return ApiResponse.ok(service.getTodaysMenu());
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('COUNTER','ADMIN')")
    public ApiResponse<List<MenuItemResponse>> all() {
        return ApiResponse.ok(service.getAll());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('COUNTER','ADMIN')")
    public ApiResponse<MenuItemResponse> create(@Valid @RequestBody MenuItemRequest req) {
        return ApiResponse.ok("Item created", service.create(req));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('COUNTER','ADMIN')")
    public ApiResponse<MenuItemResponse> update(@PathVariable Long id,
                                                @Valid @RequestBody MenuItemRequest req) {
        return ApiResponse.ok("Item updated", service.update(id, req));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.ok("Item deleted", null);
    }
}
