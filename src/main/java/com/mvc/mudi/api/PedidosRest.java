package com.mvc.mudi.api;

import com.mvc.mudi.model.Pedido;
import com.mvc.mudi.model.StatusPedido;
import com.mvc.mudi.repository.PedidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidosRest {

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping("aguardando")
    public List<Pedido> getPedidosAguardandoOfertas(){

        Sort sort = Sort.by("id").descending();
        PageRequest paginacao = PageRequest.of(0, 3, sort);

        return pedidoRepository.findByStatus(StatusPedido.AGUARDANDO, paginacao);
    }



}
