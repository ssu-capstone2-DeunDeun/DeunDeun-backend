package kr.co.deundeun.groopy.config.security;

import kr.co.deundeun.groopy.config.UserPrincipal;
import kr.co.deundeun.groopy.dao.ClubAdminRepository;
import kr.co.deundeun.groopy.dao.ClubRepository;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.mail.AuthenticationFailedException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class GroupInterceptor implements HandlerInterceptor {

    @Autowired
    private ClubAdminRepository clubAdminRepository;

    @Autowired
    private ClubRepository clubRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        final Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userPrincipal.getUser().getId();

        Club club = clubRepository.findByClubName(pathVariables.get("clubName")).orElseThrow(()-> new BadRequestException("잘못된 권한"));

        System.out.println("CHECK : " + checkClubAdmin(userId, club));
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    private boolean checkClubAdmin(Long id, Club club){
        return clubAdminRepository.existsByUserIdAndClub(id, club);
    }
}
