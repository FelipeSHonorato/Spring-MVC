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

import java.security.Principal;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    //Utilizamos Autowired para indicar ao Spring que o objeto anotado éum bean dele e que queremos uma instância dele por meio de injeção de dependencia.
    private PedidoRepository pedidoRepository;

    @GetMapping ("pedido")//Anotação para informar onde será efetuado o método GET de requisição do browser
    public String home(Model model, Principal principal){
        List<Pedido> pedidos = pedidoRepository.findAllByUsuario(principal.getName());
        model.addAttribute("pedidos", pedidos);
        return "usuario/home";
    }

    @GetMapping("pedido/{status}") //Anotação para informar onde será efetuado o método GET de requisição do browser
    public String porStatus(@PathVariable("status") String status, Model model, Principal principal){
        List<Pedido> pedidos = pedidoRepository.findByStatusAndUsuario(StatusPedido.valueOf(status.toUpperCase(Locale.ROOT)), principal.getName());
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("status", status);
        return "usuario/home";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String onError(){
        return "redirect:/usuario/home";
    }
}


