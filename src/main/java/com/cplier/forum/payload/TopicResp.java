package com.cplier.forum.payload;

import com.cplier.forum.model.Topic;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

@Getter
@Setter
public class TopicResp {

  private Long id;
  private String title;
  private String message;
  private LocalDateTime createTime;

  public TopicResp(Long id, String title, String message, LocalDateTime createTime) {
    this.id = id;
    this.title = title;
    this.message = message;
    this.createTime = createTime;
  }

  public TopicResp(Topic topic) {
    this.id = topic.getTopicId();
    this.title = topic.getTitle();
    this.message = topic.getContent();
    this.createTime = topic.getCreateTime();
  }

  public static Page<TopicResp> converter(Page<Topic> topics) {
    return topics.map(TopicResp::new);
  }
}
