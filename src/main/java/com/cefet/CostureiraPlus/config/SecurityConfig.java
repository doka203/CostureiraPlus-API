package com.cefet.CostureiraPlus.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.cefet.CostureiraPlus.security.JwtAuthenticationFilter;
import com.cefet.CostureiraPlus.service.UsuarioDetailsService;

@Configuration
public class SecurityConfig {
    @Autowired
    private UsuarioDetailsService usuarioDetailsService;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Desabilita verificação CSRF para permitir POST com token JWT
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll() // Acesso ao H2 Console
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll() // Acesso
                                                                                                              // ao
                                                                                          
                        .requestMatchers(HttpMethod.GET, "/usuarios").permitAll()                   // Swagger                                                        // UI
                        .requestMatchers(HttpMethod.POST, "/usuarios").permitAll() // Permitir criação de usuário
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll() // Permitir endpoint de login
                        .requestMatchers(HttpMethod.GET, "/clientes").hasAnyRole("ADMIN") 
                        // Regras de Autorização para Visitas
                        .requestMatchers(HttpMethod.GET, "/visitas/{id}").hasAnyRole( "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/visitas/agenda").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/visitas").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/visitas").hasAnyRole( "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/visitas/{id}").hasAnyRole( "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/visitas/{id}").hasRole("ADMIN")
                        // Regras de Autorização para Pagamentos
                        .requestMatchers(HttpMethod.GET, "/pagamentos/{id}").hasAnyRole( "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/pagamentos").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/pagamentos").hasAnyRole( "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/pagamentos/{id}").hasAnyRole( "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/pagamentos/{id}/registrar-pagamentos").hasAnyRole( "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/pagamentos/{id}").hasRole("ADMIN")
                        // Regras de Autorização para Pedidos
                        .requestMatchers(HttpMethod.GET, "/pedidos/{id}").hasAnyRole( "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/pedidos").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/pedidos/{id}/pagamentos").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/pedidos/{id}/lembretes").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/pedidos").hasAnyRole( "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/pedidos/{id}").hasAnyRole( "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/pedidos/{id}/cancelar").hasAnyRole( "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/pedidos/{id}").hasRole("ADMIN")
                        // Regras de Autorização para Lembrete
                        .requestMatchers(HttpMethod.GET, "/lembretes/{id}").hasAnyRole( "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/lembretes").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/lembretes").hasAnyRole( "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/lembretes/{id}").hasAnyRole( "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/lembretes/{id}").hasRole("ADMIN")


                        .anyRequest().authenticated() // Todos os outros endpoints exigem autenticação
                )
                .headers(headers -> headers.frameOptions().disable()) // Para H2 Console
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(usuarioDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}
