package ua.training.web;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.training.config.RootConfig;
import ua.training.config.WebAppInitializer;
import ua.training.config.WebSecurityConfig;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.Filter;
import java.util.Date;

// TODO remove root config
//@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebAppInitializer.class, WebSecurityConfig.class, RootConfig.class})
@WebAppConfiguration
@ActiveProfiles("test")
public class WebSecurityTest {

    public static final String ADMIN_URL = "/periodicals/users";
    public static final String LOGIN_URL = "/login";
    public static final String REGISTER_URL = "/periodicals/register";
    public static final String PROFILE_URL = "/periodicals/profile";
    public static final String LOGOUT_URL = "/logout";

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private Filter springSecurityFilterChain;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilter(springSecurityFilterChain)
                .build();

//        System.out.println(context.getBean("HomeController"));
    }

    @Test
    public void shouldValidateCsrf() throws Exception {
        mvc.perform(post(REGISTER_URL)
                .with(csrf())
        .param("username", "stepan")
        .param("password", "sadasads")
        .param("email", "step@pan.com")
        .param("firstName", "stepan")
                .param("lastName", "yersh0")
        .param("registrationDate", new Date().toString()))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldRejectInvalidCsrf() throws Exception {
        mvc.perform(post(REGISTER_URL)
                .with(csrf().useInvalidToken().asHeader()))
                .andExpect(status().isForbidden());

    }

    @Test
    public void shouldAuthenticateUserWithRightCredentials() throws Exception {
        mvc.perform(formLogin(LOGIN_URL).user("admin").password("pass1111"))
                .andExpect(authenticated());
    }

    @Test
    public void shouldNotAuthenticateUserWithWrongCredentials() throws Exception {
        mvc.perform(formLogin(LOGIN_URL).password("invalid"))
                .andExpect(unauthenticated());
    }

    @Test
    public void shouldLogout() throws Exception {
        mvc.perform(logout(LOGOUT_URL))
                .andExpect(unauthenticated());
    }

    @Test
    public void shouldGiveAccessToAdminPartForAdmin() throws Exception {
        mvc.perform(get(ADMIN_URL).with(admin()))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotGiveAccessToAdminPartForUser() throws Exception {
        mvc.perform(get(ADMIN_URL).with(stepan()))
                .andExpect(status().isForbidden());
    }

    @Test
    public void shouldGiveAccessToProfilePageForAdmin() throws Exception {
        mvc.perform(get(PROFILE_URL).with(admin()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldGiveAccessToProfilePageForUser() throws Exception {
        mvc.perform(get("/periodicals/senpai").with(stepan()))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotGiveAccessToAdminPartForAnonymous() throws Exception {
        mvc.perform(get(ADMIN_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void shouldNotGiveAccessToProfilePageForAnonymous() throws Exception {
        mvc.perform(get(PROFILE_URL))
                .andExpect(status().isUnauthorized());
    }

    private RequestPostProcessor admin() {
        return user("admin")
                .password("secret1")
                .roles("USER", "ADMIN");
    }

    private RequestPostProcessor stepan() {
        return user("stepan")
                .password("evjaik11")
                .roles("USER");
    }

}
