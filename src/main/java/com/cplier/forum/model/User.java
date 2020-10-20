package com.cplier.forum.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/** @author ehcayen */
@Table(name = "t_user")
@Entity
@Getter
@Setter
public class User extends AuditModel implements UserDetails {

    @Id
    @SequenceGenerator(
        name = "t_user_user_id_seq",
        sequenceName = "t_user_user_id_seq",
        allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_user_user_id_seq")
  @Column(name = "user_id")
  private Long userId;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(length = 30, nullable = false, unique = true)
  private String username;

  @Column(length = 60, nullable = false)
  private String password;

  @Column(name = "is_active")
  private boolean active;

  @Column(name = "is_removed")
  private boolean removed;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumn
  private UserAdditionalInfo info;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(
      name = "t_role_of_user",
      joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
      inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "role_id")})
  private List<Role> roles = new ArrayList<>();

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
