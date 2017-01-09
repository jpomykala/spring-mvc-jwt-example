package me.jpomykala.model.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.collect.Sets;
import lombok.*;
import me.jpomykala.model.user.view.UserView;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

import static me.jpomykala.model.user.UserRole.ROLE_ADMIN;
import static me.jpomykala.model.user.UserRole.ROLE_USER;

/**
 * Created by Evelan-E6540 on 29/08/2015.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USERS")
@Builder
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable, UserDetails {

    private static final long serialVersionUID = 2427238057150579366L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @JsonView({UserView.FullView.class})
    @Column(name = "first_name")
    private String firstName;

    @Column(unique = true)
    @Email
    @JsonView({UserView.FullView.class})
    private String email;

    @CreatedDate
    @JsonView({UserView.FullView.class})
    private Date registered;

    private Boolean enabled;

    @JsonView({UserView.FullView.class})
    private transient String token;

    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "USER_ROLES")
    @Column(name = "user_roles")
    Set<UserRole> userRoles = new HashSet<>(Sets.newHashSet(ROLE_USER));

    @Version
    private Long version;

    public boolean hasRoles(UserRole... roles) {

        for (UserRole role : roles) {
            if (!userRoles.contains(role)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        if (hasRoles(ROLE_USER)) {
            grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_USER.name()));
        }

        if (hasRoles(ROLE_ADMIN)) {
            grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_ADMIN.name()));
        }
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}

