package com.cplier.forum.controller;

import com.cplier.forum.payload.TopicResp;
import com.cplier.forum.repository.TopicRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/** @author ehcayen */
@RestController
@RequestMapping("/topic")
public class TopicEndpoint {

  @Resource private TopicRepository topicRepository;

  @GetMapping
  public Page<TopicResp> listAll(
      @RequestParam(required = false) String sectionName,
      @PageableDefault(sort = "topicId", direction = Sort.Direction.ASC) Pageable pageable) {
    if (sectionName == null) {
      return TopicResp.converter(topicRepository.findAll(pageable));
    } else {
      return TopicResp.converter(topicRepository.findBySectionName(sectionName, pageable));
    }
  }
}
