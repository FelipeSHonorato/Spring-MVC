package com.mvc.mudi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // Método de configuração do login
    @Override
    protected void configure (HttpSecurity http) throws Exception{

        http
                .authorizeRequests()
                //Código para liberar acesso a todos na página citada no caso /home
                .antMatchers("/home/**")
                .permitAll()
                //Código para forçar autenticação do usuário
                .anyRequest().authenticated()
                .and()
                .formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/usuario/pedido", true).permitAll())
                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/home"))

                .csrf().disable();
    }

    /** Método de autenticação do usuário em memória, sem banco de dados.
     @Bean
     @Override
     public UserDetailsService userDetailsService() {
     UserDetails user =
     User.withDefaultPasswordEncoder()
     .username("root")
     .password("root")
     .roles("ADM")
     .build();

     return new InMemoryUserDetailsManager(user);
     }
     **/

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

//        UserDetails user =
//            User.builder()
//                    .username("Maira")
//                    .password(encoder.encode("maira"))
//                    .roles("ADM")
//                    .build();

        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(encoder); //.withUser(user)
    }

}
