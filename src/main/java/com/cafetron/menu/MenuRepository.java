package com.cafetron.menu;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<MenuItem, Long> {

    /** Items an employee can actually order today. */
    List<MenuItem> findByAvailableTrueAndStockQuantityGreaterThan(int minStock);

    /**
     * Pessimistic lock used by Module 3 inside the order transaction so two
     * concurrent orders cannot both decrement the last unit of stock.
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select m from MenuItem m where m.id = :id")
    Optional<MenuItem> findByIdForUpdate(@Param("id") Long id);
}
