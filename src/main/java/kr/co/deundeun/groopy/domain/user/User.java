package kr.co.deundeun.groopy.domain.user;

import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.image.UserImage;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class User extends BaseEntity {

    private String name;

    private String nickname;

    private String phoneNumber;

    @Email
    @Column(nullable = false)
    private String email;

    @OneToOne
    private UserImage userImage;

    @OneToOne
    private UserHistory userHistory;

    @OneToOne
    private UserSecurity userSecurity;

    @Builder
    public User(UserSecurity userSecurity, String email) {
        this.userSecurity = userSecurity;
        this.email = email;
    }
}
