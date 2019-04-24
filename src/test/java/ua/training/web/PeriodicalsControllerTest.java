package ua.training.web;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ua.training.data.UserRepository;
import ua.training.model.User;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class PeriodicalsControllerTest {

    private PeriodicalsController controller;

    private MockMvc mockMvc;

    @Test
    public void shouldShowRegisterForm() throws Exception {
        UserRepository mockRepository = mock(UserRepository.class);
        controller = new PeriodicalsController(mockRepository);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/periodicals/register"))
                .andExpect(view().name("registerForm"));
    }

    @Test
    public void shouldProcessRegistration() throws Exception {
        User unsaved = new User("MrDVader", "iAmYourFather", "mrDart13@farGalaxy.com", "Dart", "Vader");
        User saved = new User(42L, "MrDVader", "iAmYourFather", "mrDart13@farGalaxy.com","Dart", "Vader");

        UserRepository mockRepository = mock(UserRepository.class);
        when(mockRepository.save(unsaved)).thenReturn(saved);

        controller = new PeriodicalsController(mockRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(
                post("/periodicals/register")
        .param("firstName", "Dart")
        .param("lastName", "Vader")
        .param("username", "MrDVader")
        .param("password", "iAmYourFather"))
                .andExpect(redirectedUrl("/periodicals/MrDVader"));
    }
}