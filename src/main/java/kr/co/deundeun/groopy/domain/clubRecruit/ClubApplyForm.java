package kr.co.deundeun.groopy.domain.clubRecruit;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import kr.co.deundeun.groopy.domain.BaseEntity;

@Entity
public class ClubApplyForm extends BaseEntity {

  @OneToMany(mappedBy = "clubApplyForm")
  private List<ClubRecruit> clubRecruits = new ArrayList<>();




}
