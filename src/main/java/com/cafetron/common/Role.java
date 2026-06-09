package com.cafetron.common;

/**
 * The three roles enforced by role-based access control.
 * Spring Security authorities are prefixed with ROLE_ automatically
 * when you use hasRole("EMPLOYEE").
 */
public enum Role {
    EMPLOYEE,
    COUNTER,
    ADMIN
}
