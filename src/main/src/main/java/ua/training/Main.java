package ua.training;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.training.model.Dependency;
import ua.training.model.Single;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("ua.training.model");
        Single single = context.getBean("single", Single.class);
        single.doSomethingWithDependency(context.getBean("dependency", Dependency.class));
        single.doSomethingWithDependency(context.getBean("dependency", Dependency.class));
        single.doSomethingWithDependency(context.getBean("dependency", Dependency.class));
    }

}
