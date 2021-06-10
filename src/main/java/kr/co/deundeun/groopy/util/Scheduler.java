package kr.co.deundeun.groopy.util;

import kr.co.deundeun.groopy.service.ClubRecruitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class Scheduler {

    private final ClubRecruitService clubRecruitService;

    @Scheduled(cron = "0 30 0 * * *")
    public void updateRecruitStatus(){
        clubRecruitService.scheduling();
    }

}
