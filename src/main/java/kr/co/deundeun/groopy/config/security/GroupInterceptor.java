package kr.co.deundeun.groopy.config.security;

import kr.co.deundeun.groopy.dao.ClubAdminRepository;
import kr.co.deundeun.groopy.dao.ClubRepository;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.exception.BadRequestException;
import kr.co.deundeun.groopy.exception.ClubAdminException;
import kr.co.deundeun.groopy.exception.ClubNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class GroupInterceptor implements HandlerInterceptor {

    @Autowired
    private ClubAdminRepository clubAdminRepository;

    @Autowired
    private ClubRepository clubRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        final Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(isMatchRole(auth, "ROLE_USER")){
            UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Long userId = userPrincipal.getUser().getId();
            Club club = clubRepository.findByClubName(pathVariables.get("clubName")).orElseThrow(ClubNotFoundException::new);
            if(isClubAdmin(userId, club)){
                userPrincipal.updateRoles("ROLE_GROUP");
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), null, userPrincipal.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    private boolean isClubAdmin(Long id, Club club){
        return clubAdminRepository.existsByUserIdAndClub(id, club);
    }

    private boolean isMatchRole(Authentication authentication, String role){
        Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
        return roles.stream().anyMatch(authority -> authority.getAuthority().equals(role));
    }
}
