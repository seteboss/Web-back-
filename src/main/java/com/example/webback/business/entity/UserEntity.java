package com.example.webback.business.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Data
@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(generator = "UserUUIDGenerator")
    @GenericGenerator(name = "UserUUIDGenerator", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Email
    @NotEmpty
    @Column(name = "username")
    private String username;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "uri")
    private String photoUri;

    @Column(name = "growth")
    private Integer growth;

    @Column(name = "weight")
    private Double weight;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotEmpty
    @Column(name = "user_password")
    private String password;

    @Column(name = "account_confirmed")
    private boolean accountConfirmed;

    @Column(name = "account_expired")
    private boolean accountExpired;

    @Column(name = "account_locked")
    private boolean accountLocked;

    @Column(name = "credentials_expired")
    private boolean credentialsExpired;

    @Column(name = "enabled")
    private boolean enabled;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "diets_id", referencedColumnName = "id")
    private DietsEntity diets;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "workouts_id", referencedColumnName = "id")
    private WorkoutsEntity workouts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<GraghPointEntity> pointEntities;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user2authority",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id",
                    referencedColumnName = "id"))
    @OrderBy
    private Set<AuthorityEntity> userAuthorities;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userAuthorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return !isAccountExpired();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return !isAccountLocked();
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return !isCredentialsExpired();
    }

    public void addAuthority(AuthorityEntity authority) {
        if (userAuthorities == null) {
            userAuthorities = new HashSet<>();
        }
        userAuthorities.add(authority);
    }

    public void deleteAuthority(AuthorityEntity authority) {
        userAuthorities.remove(authority);
    }


}
