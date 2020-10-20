package com.cplier.forum.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.TimeZone;

/** @author ehcayen */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class AuditModel implements Serializable {
  @Column(name = "create_time", nullable = false, updatable = false)
  @CreatedDate
  private LocalDateTime createTime;

  @Column(name = "modify_time", nullable = false)
  @LastModifiedDate
  private LocalDateTime modifyTime;

  @PostConstruct
  public void setTimeZone() {
    TimeZone.setDefault(TimeZone.getDefault());
  }

  @PrePersist
  protected void onCreate() {
    this.createTime = LocalDateTime.now();
    this.modifyTime = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    this.modifyTime = LocalDateTime.now();
  }
}
