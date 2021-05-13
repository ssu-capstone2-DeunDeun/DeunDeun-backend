package kr.co.deundeun.groopy.config.security;

import kr.co.deundeun.groopy.config.UserPrincipal;
import kr.co.deundeun.groopy.dao.UserRepository;
import kr.co.deundeun.groopy.domain.user.UserInfo;
import kr.co.deundeun.groopy.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        UserInfo userInfo = userRepository.findByEmail(email)
                                          .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with email : " + email)
        );

        return UserPrincipal.create(userInfo);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        UserInfo userInfo = userRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("User", "id", id)
        );

        return UserPrincipal.create(userInfo);
    }
}