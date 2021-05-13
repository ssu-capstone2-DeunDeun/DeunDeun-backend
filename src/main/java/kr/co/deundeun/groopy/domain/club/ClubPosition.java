package kr.co.deundeun.groopy.domain.club;


import javax.persistence.*;

@Entity
public class ClubPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Club club;

    private String positionName;
}
