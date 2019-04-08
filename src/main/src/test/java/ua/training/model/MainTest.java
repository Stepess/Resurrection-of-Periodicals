package ua.training.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ua.training.config.AppConfig;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class MainTest {

    @Autowired
    private Main main;

    @Test
    public void shouldInjectDependency() {
        System.out.println(main);
        assertNotNull(main);
    }

}