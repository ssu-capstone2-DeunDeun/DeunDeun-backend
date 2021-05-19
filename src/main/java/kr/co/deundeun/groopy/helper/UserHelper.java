package kr.co.deundeun.groopy.helper;

import kr.co.deundeun.groopy.dao.UserRepository;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.exception.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserHelper {

  public static User findUserById(UserRepository userRepository, Long id) {
    return userRepository.findById(id)
                         .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
  }

  public static User findUserByEmail(UserRepository userRepository, String email) {
    return userRepository.findByEmail(email)
                         .orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + email));
  }
}
