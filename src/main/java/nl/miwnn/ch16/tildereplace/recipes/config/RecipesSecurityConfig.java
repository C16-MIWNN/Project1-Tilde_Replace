package nl.miwnn.ch16.tildereplace.recipes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class RecipesSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests((request) -> request
                        .requestMatchers("/", "/recipeOverview").permitAll()
                        .requestMatchers("/recipe/search").permitAll()
                        .requestMatchers("/food/overview").permitAll()
                        .requestMatchers("/user/new", "user/save", "/user/login").permitAll()
                        .requestMatchers("/tags/**").permitAll()
                        .requestMatchers("/webjars/**", "/css/**").permitAll()
                        .requestMatchers("/image/**").permitAll()
                        .anyRequest().authenticated()
                    )
                .formLogin(form -> form.loginPage("/user/login").permitAll())
                .logout((logout) -> logout.logoutSuccessUrl("/"));

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
