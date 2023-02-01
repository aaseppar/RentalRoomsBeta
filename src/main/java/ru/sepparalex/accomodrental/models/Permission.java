package ru.sepparalex.accomodrental.models;

public enum Permission {
    CLIENT_READ("client:read"),
    CLIENT_WRITE("client:write"),
    CLIENT_CREATE("client:write"),
    CLIENT_SET_ROLE("client:set_role"),
    BOOKING_READ("booking:read"),
    BOOKING_WRITE("booking:write"),
    ROOMS_READ("rooms:read"),

    ROOMS_WRITE("rooms:write"),
    CITY_WRITE("city:write"),
    CITY_READ("city:read");
    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}