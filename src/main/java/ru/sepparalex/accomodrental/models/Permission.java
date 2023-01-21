package ru.sepparalex.accomodrental.models;

public enum Permission {
    CLIENT_READ("client:read"),
    CLIENT_WRITE("client:write");
    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}