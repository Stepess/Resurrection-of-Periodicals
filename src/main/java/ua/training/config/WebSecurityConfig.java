package ua.training.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.training.data.UserRepository;
import ua.training.service.UserDetailsServiceImpl;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

   // @Autowired
   // private UserRepository userRepository;


    //DB auth
    /*@Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
"select username, password, true " +
"from Spitter where username=?")
.authoritiesByUsernameQuery(
"select username, 'ROLE_USER' from Spitter where username=?")
.passwordEncoder(new StandardPasswordEncoder("53cr3t"));
    }*/

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository, PasswordEncoder encoder) {
        return new UserDetailsServiceImpl(userRepository, encoder);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser("user").password(encoder.encode("password")).roles("USER")
                .and()
                .withUser("admin").password(encoder.encode("1111")).roles("USER", "ADMIN");
        auth.userDetailsService();
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/authenticate")
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/periodicals/me")
                .usernameParameter("login")
                .passwordParameter("password")
                .permitAll()
                //.successForwardUrl()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .and()
                .authorizeRequests()
                .antMatchers("/periodicals/users/**").hasRole("ADMIN")
                .antMatchers("/periodicals/**", "/periodicals/me").authenticated()
                .antMatchers(HttpMethod.POST, "/periodicals").authenticated()
                .antMatchers(HttpMethod.POST, "/periodicals/register").permitAll()
                .anyRequest().permitAll()
                // to require https
                .and()
                .requiresChannel()
                .antMatchers("/spitter/form").requiresSecure()
                .and()
                .httpBasic()
                .realmName("periodicals")
                .and()
                .rememberMe()
                .tokenValiditySeconds(2419200)
                .key("periodicals13")
                .and()
                .csrf();

                // for h2 console only
                  //      .antMatchers("/").permitAll()
                //.antMatchers("/console/**").permitAll()
       // http.csrf().disable();
        //http.headers().frameOptions().disable();
        /*<input id="remember_me" name="remember-me" type="checkbox"/>
<label for="remember_me" class="inline">Remember me</label>*/
    }
}
