package com.cplier.forum.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

/** @author ehcayen */
@Table(name = "t_role")
@Entity
@Getter
@Setter
public class Role implements GrantedAuthority, Serializable {

  @Id
  @SequenceGenerator(
      name = "t_role_role_id_seq",
      sequenceName = "t_role_role_id_seq",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_role_role_id_seq")
  @Column(name = "role_id")
  private Long roleId;

  @Column(name = "name")
  private String name;

  @Column(name = "description", columnDefinition = "TEXT")
  private String description;

  @Override
  public String getAuthority() {
    return name;
  }
}
