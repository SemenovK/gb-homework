package gb.spring.homework11.controller;

import gb.spring.homework11.component.CurrentUser;
import gb.spring.homework11.model.User;
import gb.spring.homework11.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping
@AllArgsConstructor
public class ScoreController {
    private final String BASE_PATH = "/app/score";

    CurrentUser currentUser;
    UserService userService;

    /**
     * GET .../app/score/inc - увеличивает балл текущего пользователя
     * GET .../app/score/dec - уменьшает балл текущего пользователя
     * GET .../app/score/get/current - показывает балл вошедшего пользователя
     * GET .../app/score/get/{id} - показывает балл пользователя с указанным id (доступно
     */


    @GetMapping(BASE_PATH + "/inc")
    public String increase() {
        currentUser.increaseScore();
        return currentUser.getInfo();
    }

    @GetMapping(BASE_PATH + "/dec")
    public String decrease() {
        currentUser.decreaseScore();
        return currentUser.getInfo();
    }


    @GetMapping(BASE_PATH + "/get/current")
    public String getUserScore() {
        return currentUser.getInfo();
    }

    //Для успешного редиректа после логина
    @PostMapping(BASE_PATH + "/get/current")
    public String getUserScorePOST() {
        return currentUser.getInfo();
    }

    @GetMapping(BASE_PATH + "/get/{id}")
    public String getUserScore(@PathVariable(name = "id") BigInteger userId) {
        return userService.getUserScoreById(userId);
    }
}
