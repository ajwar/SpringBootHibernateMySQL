package ru.yandex.ajwar.controllers;

/**
 * Created by Ajwar on 12.02.2017.
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "Добро пожаловать в тестовое приложение для Beatdev!";
    }

}
