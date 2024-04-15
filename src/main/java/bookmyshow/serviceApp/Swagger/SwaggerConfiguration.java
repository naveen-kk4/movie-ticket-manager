package bookmyshow.serviceApp.Swagger;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class SwaggerConfiguration {



    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().
                info(new Info().title("Movie Ticket Manager")
                        .description("Movie Ticket Manager API documentation").
                        version("1.0").
                        contact(new Contact().name("Naveen K").email("naveennig2001@gmail.com")).
                        summary("A robust backend application developed using Spring Boot and MySQL for data storage. It offers a comprehensive set of 15+ API endpoints to facilitate seamless management of movie tickets. Users can book and manage tickets,retrieve movie details and many more...")
                        .license(new License().name("Apache"))
                );

    }


}
