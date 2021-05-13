package kr.co.deundeun.groopy.domain.club;

import javax.persistence.*;

@Entity
public class ClubAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Club club;

    @Column(nullable = false)
    private Long userId;
}
