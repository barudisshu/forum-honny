package com.cplier.forum.repository;

import com.cplier.forum.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/** @author ehcayen */
public interface TopicRepository extends JpaRepository<Topic, Long> {
  /**
   * 根据类别查询主题信息
   *
   * @param sectionName 类别
   * @param pageable 分页
   * @return 分页结果
   */
  @Query("select t from Topic t join t.section s where s.name like %?1%")
  Page<Topic> findBySectionName(String sectionName, Pageable pageable);
}
