package com.leafaries.financemanagerbackend.user;

import com.leafaries.financemanagerbackend.wallet.Wallet;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Entity class representing a user.
 * Implements UserDetails for Spring Security integration.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
public class User implements UserDetails {
    /**
     * The ID of the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The username of the user.
     * Must be unique and not null.
     */
    @Column(nullable = false, unique = true)
    private String username;

    /**
     * The password of the user.
     * Must not be null.
     */
    @Column(nullable = false)
    private String password;

    /**
     * The list of wallets owned by the user.
     * Mapped by the "user" field in the Wallet entity.
     * Cascade type ALL and orpahn removal enabled.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Wallet> wallets = new ArrayList<>();

    /**
     * Gets the authorities granted to the user.
     *
     * @return an empty list of authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    /**
     * Checks if the user's account is non-expired.
     *
     * @return true if the account is non-expired
     */
    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    /**
     * Checks if the user's account is non-locked.
     *
     * @return true if the account is non-locked
     */
    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    /**
     * Checks if the user's credentials are non-expired.
     *
     * @return true if the credentials are non-expired
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    /**
     * Checks if the user is enabled.
     *
     * @return true if the user is enabled
     */
    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
