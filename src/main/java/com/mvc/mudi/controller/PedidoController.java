package com.mvc.mudi.controller;

import com.mvc.mudi.dto.RequisicaoNovoPedido;
import com.mvc.mudi.model.Pedido;
import com.mvc.mudi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping("formulario")
    public String formulario(){
        return "/pedido/formulario";
    }

    @PostMapping("novo")
    public String novo(RequisicaoNovoPedido requisicao){

        Pedido pedido = requisicao.toPedido();
        pedidoRepository.save(pedido);
        return "/pedido/formulario";
    }
}
