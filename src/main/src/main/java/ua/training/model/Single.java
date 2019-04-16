package ua.training.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Single {

    private Dependency previous;

    @Autowired
    public void doSomethingWithDependency(Dependency dependency) {
        System.out.println("To string " + dependency);
        System.out.println("Dependency class - " + dependency.getClass());

        if (previous != null) {
            System.out.println("Equality with previous " + (dependency == previous));
        }

        this.previous = dependency;
        System.out.println();
    }

}
