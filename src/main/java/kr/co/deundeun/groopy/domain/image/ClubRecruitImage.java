package kr.co.deundeun.groopy.domain.image;

import kr.co.deundeun.groopy.domain.club.ClubRecruit;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Getter
@Entity
public class ClubRecruitImage extends Image{

    @ManyToOne
    private ClubRecruit clubRecruit;

}
