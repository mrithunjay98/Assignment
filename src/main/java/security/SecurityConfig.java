package security;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withUsername("reader")
                        .password("{noop}password")
                        .roles("READER")
                        .build(),
                User.withUsername("readwrite")
                        .password("{noop}password")
                        .roles("READER", "WRITER")
                        .build()
        );
    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests(authorizeRequests ->
//                authorizeRequests
//                    .antMatchers("/synonyms/**").hasRole("READER")
//                    .anyRequest().authenticated()
//            )
//            .oauth2ResourceServer(oauth2ResourceServer ->
//                oauth2ResourceServer
//                    .jwt(jwtConfigurer -> {})
//            );
//    }
}

