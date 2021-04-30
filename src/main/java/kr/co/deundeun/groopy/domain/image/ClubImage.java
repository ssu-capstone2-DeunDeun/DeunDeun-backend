package kr.co.deundeun.groopy.domain.image;

import kr.co.deundeun.groopy.domain.club.Club;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Getter
@Entity
public class ClubImage extends Image{

    @ManyToOne
    private Club club;

}
