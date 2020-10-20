package com.cplier.forum.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/** @author ehcayen */
@Entity
@Setter
@Getter
public class Post extends AuditModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long postId;

  @Column(columnDefinition = "TEXT")
  private String content;


}
