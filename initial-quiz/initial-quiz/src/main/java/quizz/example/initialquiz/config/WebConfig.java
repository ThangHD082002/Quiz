package quizz.example.initialquiz.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Cho phép CORS cho tất cả các đường dẫn
                .allowedOriginPatterns("*") // Cho phép truy cập từ tất cả các origin
                .allowedMethods("*") // Cho phép tất cả các phương thức HTTP
                .allowCredentials(true); // Cho phép sử dụng cookie (nếu cần)
    }
}

