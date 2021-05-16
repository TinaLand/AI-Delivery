package AIdeliverer.com.example.deliverer.security.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Qualifier("userDetailsServiceImpl")
//    @Autowired
//    private UserDetailsService userDetailsService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v*/**")
                .permitAll()
                .anyRequest()
                .authenticated().and()
                .formLogin()
                .and()
                .logout()
                .and()
                .exceptionHandling()
                    .accessDeniedPage("/403");
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
////                .antMatchers("/", "/api/v*/*").permitAll()
//                .antMatchers("/api/v*/*").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                    .loginProcessingUrl("/api/v*/user")
//                    .defaultSuccessUrl("/")
//                    .failureUrl("/login?error")
//                    .permitAll()
//                    .and()
//                .logout()
//                    .permitAll()
//                    .logoutSuccessUrl("/api/v*/user/login")
//                    .invalidateHttpSession(true)
//                    .deleteCookies("JSESSIONID")
//                    .and()
//                .exceptionHandling()
//                    .accessDeniedPage("/403");
//    }


//    @Bean
//    public AuthenticationManager customAuthenticationManager() throws Exception {
//        return authenticationManager();
//    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
//    }

}
