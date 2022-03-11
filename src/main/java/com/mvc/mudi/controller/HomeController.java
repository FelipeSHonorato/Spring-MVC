package com.mvc.mudi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //Anotação para criar uma classe que será controller
public class HomeController {

    @GetMapping("/home") //Anotação para informar onde será efetuado o método GET de requisição do browser
    public String home(){
        return "home";
    }
}
