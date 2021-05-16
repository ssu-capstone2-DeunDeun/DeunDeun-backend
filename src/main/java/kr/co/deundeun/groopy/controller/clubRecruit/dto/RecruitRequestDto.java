package kr.co.deundeun.groopy.controller.clubRecruit.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class RecruitRequestDto {

    private String title;
    private String content;
    private Date fromDate;

}
