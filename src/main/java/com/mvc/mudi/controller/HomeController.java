package com.mvc.mudi.controller;

import com.mvc.mudi.model.Pedido;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Controller //Anotação para criar uma classe que será controller
public class HomeController {

    @GetMapping("/home") //Anotação para informar onde será efetuado o método GET de requisição do browser
    public String home(Model model){

        Pedido pedido = new Pedido();
        pedido.setNomeProduto("Ghost of Tsushima Versão do Diretor - PS4");
        pedido.setUrlImagem("https://m.media-amazon.com/images/I/714PDNSlipS._AC_SX522_.jpg");
        pedido.setUrlProduto("https://www.amazon.com.br/Ghost-Tsushima-Vers%C3%A3o-Diretor-Playstation/dp/B099H37QKP/?_encoding=UTF8&pd_rd_w=Ak6Gy&pf_rd_p=d510337a-570c-40ee-9125-b51bd083f7fb&pf_rd_r=58YCE1FERTN347RFYA3W&pd_rd_r=969ebb88-9bbd-4180-b4c3-55215e6be8dc&pd_rd_wg=lwuze&ref_=pd_gw_crs_zg_bs_7791985011&th=1");
        pedido.setDescricao("No final do século XIII, o Império Mongol destruiu nações inteiras em sua campanha para conquistar o Oriente. A ilha de Tsushima é tudo o que resta entre o Japão e uma enorme invasão mongol liderada por um general ardiloso e implacável, Khotun Khan. Em meio à ilha devastada pela primeira onda de ataques mongóis está Jin Sakai, um guerreiro samurai, um dos últimos sobreviventes de seu clã. Ele está decidido a fazer o que for preciso, custe o que custar, para proteger seu povo e recuperar seu lar. Jin deve deixar de lado as tradições que o moldaram como guerreiro e forjar um novo caminho, o do Fantasma, travando uma guerra atípica pela liberdade do Japão.");
        pedido.setValorNegociado(BigDecimal.valueOf(175.96));

        //Incluindo o objeto para uma list e enviando dados para View através do model.
        List<Pedido> pedidos = Arrays.asList(pedido);
        model.addAttribute("pedidos", pedidos);

        return "home";
    }
}
