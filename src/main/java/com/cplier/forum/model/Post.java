package com.cplier.forum.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 贴子
 *
 * @author ehcayen
 */
@Table(name = "t_post")
@Entity
@Setter
@Getter
public class Post extends AuditModel {
  @Id
  @SequenceGenerator(
      name = "t_post_post_id_seq",
      sequenceName = "t_post_post_id_seq",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_post_post_id_seq")
  @Column(name = "post_id")
  private Long postId;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "topic_id", referencedColumnName = "topic_id")
  private Topic topic;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id", referencedColumnName = "user_id")
  private User user;

  @Column(name = "content", columnDefinition = "TEXT")
  private String content;
}
