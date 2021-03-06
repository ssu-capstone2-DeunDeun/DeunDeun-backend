package kr.co.deundeun.groopy.domain.hashtag;

import javax.persistence.FetchType;

import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@NoArgsConstructor
@Entity
public class ClubHashtag extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Club club;

    @ManyToOne(fetch = FetchType.LAZY)
    protected HashtagInfo hashtagInfo;

    @Builder
    public ClubHashtag(Club club, HashtagInfo hashtagInfo) {
        this.club = club;
        this.hashtagInfo = hashtagInfo;
    }

    public String getHashtagName() {
        return this.hashtagInfo.getName();
    }

    public static List<ClubHashtag> ofList(Club club, List<HashtagInfo> hashtagInfos) {
        return hashtagInfos.stream()
                .map(hashtagInfo -> new ClubHashtag(club, hashtagInfo))
                .collect(Collectors.toList());
    }
}
