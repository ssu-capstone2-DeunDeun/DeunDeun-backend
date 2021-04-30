package kr.co.deundeun.groopy.domain.image;

import kr.co.deundeun.groopy.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@NoArgsConstructor
@Getter
@Entity
public class UserImage extends Image {

    @OneToOne
    private User user;

}
