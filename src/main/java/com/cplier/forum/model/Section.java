package com.cplier.forum.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/** @author ehcayen */
@Table(name = "t_section")
@Entity
@Getter
@Setter
public class Section implements Serializable {

  @Id
  @SequenceGenerator(
      name = "t_section_section_id_seq",
      sequenceName = "t_section_section_id_seq",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_section_section_id_seq")
  @Column(name = "section_id")
  private Long sectionId;

  @Column(name = "name", length = 50)
  private String name;

  @Column(name = "description", length = 150)
  private String description;
}
