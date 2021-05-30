package kr.co.deundeun.groopy.domain.club.constant;

import lombok.Getter;

@Getter
public enum CategoryType {
    IT("IT"), HEALTH("헬스/체육"), STARTUP("창업/자기계발"),
    FRIENDSHIP("친목"), VOLUNTEER("봉사"),
    CULTURE("문화"), LANGUAGE("어학"), OTHERS("기타");

    private String category;

    CategoryType(String category){
        this.category = category;
    }
}