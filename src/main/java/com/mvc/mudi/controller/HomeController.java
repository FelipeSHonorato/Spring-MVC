package com.mvc.mudi.controller;

import com.mvc.mudi.model.Pedido;
import com.mvc.mudi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller //Anotação para criar uma classe que será controller
public class HomeController {

    @Autowired //Utilizamos Autowired para indicar ao Spring que o objeto anotado éum bean dele e que queremos uma instância dele por meio de injeção de dependencia.
    private PedidoRepository pedidoRepository;

    @GetMapping("/home") //Anotação para informar onde será efetuado o método GET de requisição do browser
    public String home(Model model){
        List<Pedido> pedidos = pedidoRepository.findAll();
        model.addAttribute("pedidos", pedidos);
        return "home";
    }
}
