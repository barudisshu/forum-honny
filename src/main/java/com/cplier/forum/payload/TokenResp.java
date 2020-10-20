package com.cplier.forum.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenResp {

  private String token;
  private String type;
}
