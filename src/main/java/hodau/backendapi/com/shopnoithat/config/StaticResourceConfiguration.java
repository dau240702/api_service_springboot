package hodau.backendapi.com.shopnoithat.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class StaticResourceConfiguration implements WebMvcConfigurer {
    private static final Dotenv dotenv = Dotenv.configure().load();
    private final String uploadDir;

    public StaticResourceConfiguration() {
        this.uploadDir = dotenv.get("file.upload-dir");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + uploadDir + "/")
                .setCachePeriod(3600)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }
}
