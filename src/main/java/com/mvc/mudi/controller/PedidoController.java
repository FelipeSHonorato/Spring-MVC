package com.mvc.mudi.controller;

import com.mvc.mudi.dto.RequisicaoNovoPedido;
import com.mvc.mudi.model.Pedido;
import com.mvc.mudi.model.User;
import com.mvc.mudi.repository.PedidoRepository;
import com.mvc.mudi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("formulario")
    public String formulario(RequisicaoNovoPedido requisicao){
        return "pedido/formulario";
    }

    @PostMapping("novo")
    public String novo(@Valid RequisicaoNovoPedido requisicao, BindingResult result){
   // Inserido no parametros a anotação @Valid para informar ao Java que esse método tem validações e BidingResult para indicar que haverá uma ação caso encontre o erro

        if(result.hasErrors()){
            return "pedido/formulario";
        }//Caso contenha algum erro o usuário ao clicar no botão de Cadastrar ele voltará para o formulário de cadastro


        //Buscamos o usuário que está logado no momento para ser vinculado ao pedido que for criado naquele momento
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);

        Pedido pedido = requisicao.toPedido();
        pedido.setUser(user);
        pedidoRepository.save(pedido);
        return "redirect:/usuario/pedido";
    }
}
