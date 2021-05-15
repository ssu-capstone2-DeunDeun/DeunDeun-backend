package kr.co.deundeun.groopy.domain.club;

import javax.persistence.*;
import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.user.User;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class ClubAdmin extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Club club;

    @JoinColumn(nullable = false)
    private Long userId;

    @Builder
    public ClubAdmin(Club club, Long userId){
        this.club = club;
        this.userId = userId;
    }
}
