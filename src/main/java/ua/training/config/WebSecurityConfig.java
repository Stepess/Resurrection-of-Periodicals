package ua.training.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


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


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser("user").password(encoder.encode("password")).roles("USER").and()
                .withUser("admin").password(encoder.encode("1111")).roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .logoutUrl("/logout")
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/console/**").permitAll()
                //.authorizeRequests()
                /*.antMatchers("/periodicals/me").authenticated()
                .antMatchers(HttpMethod.POST, "/periodicals").authenticated()
                .antMatchers("/periodicals/**", "/periodicals/mine").authenticated()*/
                //.anyRequest().permitAll()
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
                .key("periodicals13");

        http.csrf().disable();
        http.headers().frameOptions().disable();
        /*<input id="remember_me" name="remember-me" type="checkbox"/>
<label for="remember_me" class="inline">Remember me</label>*/
    }
}
