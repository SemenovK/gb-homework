package gb.spring.homework11.service;

import gb.spring.homework11.component.CurrentUser;
import gb.spring.homework11.model.User;
import gb.spring.homework11.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private CurrentUser currentUser;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<GrantedAuthority> authsList = new CopyOnWriteArrayList<>();
        authsList.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "ROLE_USER";
            }
        });

        User user = userRepository.findUserByUserName(s);
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUserName(), user.getUserPassword(), authsList);

        currentUser.setUserName(user.getUserName());
        currentUser.setUserId(user.getUserId());
        currentUser.setUserScore(user.getUserScore().getScore());

        return userDetails;
    }

    public String getUserScoreById(BigInteger userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return user.getUserName() + " : " + user.getUserScore().getScore();
    }

    public void saveUserScore(BigInteger userId, int userScore) {
        User user = userRepository.getById(userId);
        user.getUserScore().setScore(userScore);
        userRepository.save(user);
    }
}
