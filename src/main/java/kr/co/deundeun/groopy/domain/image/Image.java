package kr.co.deundeun.groopy.domain.image;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import kr.co.deundeun.groopy.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Getter
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
@Entity
public abstract class Image extends BaseEntity {
    protected String imageUrl;

    public String toImageUrl(){
        return imageUrl;
    }
}
