package com.cplier.forum.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/** @author ehcayen */
@Table(name = "t_user_info")
@Entity
@Setter
@Getter
public class UserAdditionalInfo implements Serializable {

  @Id
  @Column(name = "user_id")
  private Long userId;

  @Column(name = "second_email")
  private String secondEmail;

  @Column(name = "phone", length = 15)
  private String phone;

  @Column(name = "first_name", length = 20)
  private String firstName;

  @Column(name = "last_name", length = 30)
  private String lastName;

  @Column(name = "birthday")
  private LocalDate birthday;

  @Column(name = "city", length = 20)
  private String city;

  @Column(name = "biography", length = 150)
  private String aboutMe;

  @Column(name = "footer", length = 50)
  private String footer;

  @MapsId
  @OneToOne
  @JoinColumn(name = "user_id", referencedColumnName = "user_id")
  private User user;
}
