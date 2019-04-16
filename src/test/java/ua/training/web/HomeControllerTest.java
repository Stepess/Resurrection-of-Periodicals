package ua.training.web;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class HomeControllerTest {

    private HomeController controller;

    private MockMvc mockMvc;

    @Test
    public void shouldReturnHomePage() throws Exception {
        controller = new HomeController();

        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .build();

        mockMvc.perform(get("/"))
                .andExpect(view().name("home"));
    }

}