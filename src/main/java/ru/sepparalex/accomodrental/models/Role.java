package ru.sepparalex.accomodrental.models;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Set.of(Permission.CLIENT_WRITE,Permission.BOOKING_WRITE,Permission.ROOMS_READ,Permission.ROOMS_WRITE,Permission.CITY_READ)),
    ADMIN(Set.of(Permission.CLIENT_READ, Permission.CLIENT_WRITE,Permission.BOOKING_WRITE,
            Permission.BOOKING_READ,Permission.ROOMS_READ, Permission.ROOMS_WRITE,Permission.CITY_READ,Permission.CITY_WRITE,
            Permission.CLIENT_SET_STATUS)),
    SUPERADMIN(Set.of(Permission.CLIENT_READ, Permission.CLIENT_WRITE,Permission.BOOKING_WRITE,
            Permission.BOOKING_READ,Permission.ROOMS_READ, Permission.ROOMS_WRITE,Permission.CITY_READ,Permission.CITY_WRITE,
            Permission.CLIENT_SET_ROLE,Permission.CLIENT_SET_STATUS));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<GrantedAuthority> getAuthorities() {
        return this.permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
