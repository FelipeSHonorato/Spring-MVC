package com.mvc.mudi.controller;

import com.mvc.mudi.model.Pedido;
import com.mvc.mudi.model.StatusPedido;
import com.mvc.mudi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Locale;

@Controller //Anotação para criar uma classe que será controller
@RequestMapping ("/home") //Anotação para informar que ser feita uma requisicao para /home ele será automaticamente acionado o método home
public class HomeController {

    @Autowired //Utilizamos Autowired para indicar ao Spring que o objeto anotado éum bean dele e que queremos uma instância dele por meio de injeção de dependencia.
    private PedidoRepository pedidoRepository;

    @GetMapping //Anotação para informar onde será efetuado o método GET de requisição do browser
    public String home(Model model){
        List<Pedido> pedidos = pedidoRepository.findAll();
        model.addAttribute("pedidos", pedidos);
        return "home";
    }

    @GetMapping("/{status}") //Anotação para informar onde será efetuado o método GET de requisição do browser
    public String porStatus(@PathVariable("status") String status, Model model){
        List<Pedido> pedidos = pedidoRepository.findByStatus(StatusPedido.valueOf(status.toUpperCase(Locale.ROOT)));
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("status", status);
        return "home";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String onError(){
        return "redirect:/home";
    }
}
