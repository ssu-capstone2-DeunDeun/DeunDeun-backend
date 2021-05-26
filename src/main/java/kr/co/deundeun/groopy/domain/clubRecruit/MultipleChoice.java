package kr.co.deundeun.groopy.domain.clubRecruit;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import kr.co.deundeun.groopy.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class MultipleChoice extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  private ClubRecruitQuestion clubRecruitQuestion;

  private int choiceNumber; // 선지번호

  private String choiceContent; // 선지내용

}
