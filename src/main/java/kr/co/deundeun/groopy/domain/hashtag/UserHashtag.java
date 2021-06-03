package kr.co.deundeun.groopy.domain.hashtag;

import javax.persistence.FetchType;
import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@ToString
@Getter
@Entity
public class UserHashtag extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  protected HashtagInfo hashtagInfo;

  @Builder
  public UserHashtag(User user, HashtagInfo hashtagInfo){
    this.user = user;
    this.hashtagInfo = hashtagInfo;
  }

  public static List<UserHashtag> ofList(User user, List<HashtagInfo> hashtagInfos) {
    return hashtagInfos.stream()
            .map(hashtagInfo -> new UserHashtag(user, hashtagInfo))
            .collect(Collectors.toList());
  }
}
