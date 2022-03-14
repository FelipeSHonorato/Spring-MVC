package com.mvc.mudi.controller;

import com.mvc.mudi.model.Pedido;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Controller //Anotação para criar uma classe que será controller
public class HomeController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/home") //Anotação para informar onde será efetuado o método GET de requisição do browser
    public String home(Model model){


        Query query = entityManager.createQuery("SELECT p FROM Pedido p", Pedido.class);
        List<Pedido> pedidos = query.getResultList();

        model.addAttribute("pedidos", pedidos);

        return "home";
    }
}
