package com.cplier.forum.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 主题
 *
 * @author ehcayen
 */
@Table(name = "t_topic")
@Entity
@Getter
@Setter
public class Topic extends AuditModel {

  @Id
  @SequenceGenerator(
      name = "t_topic_topic_id_seq",
      sequenceName = "t_topic_topic_id_seq",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_topic_topic_id_seq")
  @Column(name = "topic_id")
  private Long topicId;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id", referencedColumnName = "user_id")
  private User user;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "section_id", referencedColumnName = "section_id")
  private Section section;

  @Column(name = "title", length = 50)
  private String title;

  @Column(name = "content", columnDefinition = "TEXT")
  private String content;

  @Column(name = "views")
  private int views;

  @Column(name = "is_closed")
  private boolean closed;
}
