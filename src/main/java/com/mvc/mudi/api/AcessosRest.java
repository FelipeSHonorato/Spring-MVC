package com.mvc.mudi.api;

import com.mvc.mudi.interceptor.InterceptadorDeAcessos;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("acessos")
public class AcessosRest {

    @GetMapping
    public List<InterceptadorDeAcessos.Acesso> getAcessos(){
        return InterceptadorDeAcessos.acessos;
    }

}
