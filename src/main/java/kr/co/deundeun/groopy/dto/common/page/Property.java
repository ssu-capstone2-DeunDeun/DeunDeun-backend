package kr.co.deundeun.groopy.dto.common.page;

import lombok.Getter;

@Getter
public enum Property {
  DATE("createdAt"),
  LIKE("likeCount"),
  VIEW("viewCount"),
  APPLICANT("applicantCount");

  private final String column;

  Property(String column) {
    this.column = column;
  }
}
