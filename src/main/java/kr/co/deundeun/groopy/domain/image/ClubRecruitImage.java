package kr.co.deundeun.groopy.domain.image;

import javax.persistence.FetchType;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor
@Entity
public class ClubRecruitImage extends Image{
    @ManyToOne(fetch = FetchType.LAZY)
    private ClubRecruit clubRecruit;
}
