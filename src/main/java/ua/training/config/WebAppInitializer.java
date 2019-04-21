package ua.training.config;

import org.springframework.web.servlet.support.
        AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import java.io.File;

public class WebAppInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {

    private static final long MAX_UPLOAD_SIZE = 10L * 1024L * 1024L;

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        File file = new File("/tmp/uploads");

        String absolutePath = file.getAbsolutePath();

        if (file.mkdirs()) {
            System.out.println(String.format("[WARN] Note %s directory for file uploading was created!", absolutePath));
        } else {
            System.out.println(String.format("[WARN] Note files will be uploaded into %s", absolutePath));
        }

        registration.setMultipartConfig(
                new MultipartConfigElement(absolutePath, MAX_UPLOAD_SIZE, MAX_UPLOAD_SIZE * 2, 0));
    }
}
