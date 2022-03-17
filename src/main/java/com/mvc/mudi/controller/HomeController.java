package com.mvc.mudi.controller;

import com.mvc.mudi.model.Pedido;
import com.mvc.mudi.model.StatusPedido;
import com.mvc.mudi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller //Anotação para criar uma classe que será controller
@RequestMapping ("/home") //Anotação para informar que ser feita uma requisicao para /home ele será automaticamente acionado o método home
public class HomeController {

    @Autowired //Utilizamos Autowired para indicar ao Spring que o objeto anotado éum bean dele e que queremos uma instância dele por meio de injeção de dependencia.
    private PedidoRepository pedidoRepository;

    @GetMapping //Anotação para informar onde será efetuado o método GET de requisição do browser
    public String home(Model model, Principal principal){

        //Para solicitar que os pedidos sejam mostrados na tela de home em ordem de data de entrega e decrescente
        Sort sort = Sort.by("dataDaEntrega").descending();
        //Para solicitar que seja demonstrado em 2 pedidos por página
        PageRequest paginacao = PageRequest.of(0, 2, sort);

        List<Pedido> pedidos = pedidoRepository.findByStatus(StatusPedido.ENTREGUE, paginacao);
        model.addAttribute("pedidos", pedidos);
        return "/home";
    }
}
