package ru.sepparalex.accomodrental.models;

public enum Permission {
    CLIENT_READ("client:read"),
    CLIENT_WRITE("client:write"),
    BOOKING_READ("booking:read"),
    BOOKING_WRITE("booking:write"),
    ROOMS_READ("rooms:read"),

    ROOMS_WRITE("rooms:write");
    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}