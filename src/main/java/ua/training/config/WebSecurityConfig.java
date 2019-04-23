package ua.training.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER").and()
                .withUser("admin").password("1111").roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/periodicals/me").authenticated()
                .antMatchers(HttpMethod.POST, "/periodicals").authenticated()
                .antMatchers("/periodicals/**", "/periodicals/mine").authenticated()
                .anyRequest().permitAll()
                // to require https
        .and()
                .requiresChannel()
                .antMatchers("/spitter/form").requiresSecure();
    }
}
