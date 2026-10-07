package com.ritik.quiz.config;

import org.jspecify.annotations.Nullable;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * Indicates that this application does not use a separate parent Spring context.
     *
     * @return {@code null} because all beans live in the servlet application context
     */
    @Override
    protected Class<?> @Nullable [] getRootConfigClasses() {
        return null;
    }

    /**
     * Supplies the Java configuration used to create the DispatcherServlet's Spring context.
     *
     * @return the application configuration classes for Spring MVC
     */
    @Override
    protected Class<?> @Nullable [] getServletConfigClasses() {
        // Load this configuration in the DispatcherServlet's context
        return new Class<?>[]{AppConfig.class};
    }

    /**
     * Maps the DispatcherServlet to the root path so Spring MVC can handle application requests.
     *
     * @return the servlet URL mappings
     */
    @Override
    protected String[] getServletMappings() {
        // Map the DispatcherServlet to the application's root path
        return new String[]{"/"};
    }
}
