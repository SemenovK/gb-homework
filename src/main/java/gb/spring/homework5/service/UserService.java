package gb.spring.homework5.service;

import gb.spring.homework5.common.components.CurrentCustomer;
import gb.spring.homework5.model.User;
import gb.spring.homework5.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final CurrentCustomer currentCustomer;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findUserByLogin(s);
        List<GrantedAuthority> authsList = new CopyOnWriteArrayList<>();
        authsList.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "ROLE_USER";
            }
        });
        UserDetails userDetails = null;
        if (user != null) {
            userDetails = new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), authsList);
            currentCustomer.setCustomer(user.getCustomer());

        } else {
            currentCustomer.setCustomer(null);
            throw new UsernameNotFoundException("Пользователь не найден");
        }

        return userDetails;
    }
}
