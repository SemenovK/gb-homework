package gb.spring.homework11.component;

import gb.spring.homework11.model.User;
import gb.spring.homework11.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigInteger;


@RequiredArgsConstructor
@Data
@Component
@SessionScope
public class CurrentUser {

    private BigInteger userId;
    private int userScore;
    private String userName;
    private final UserService userService;

    public void increaseScore() {
        userScore++;
        userService.saveUserScore(userId, userScore);
    }

    public void decreaseScore() {
        userScore--;
        userService.saveUserScore(userId, userScore);
    }

    public String getInfo() {
        return userName + " : " + userScore;
    }
}
