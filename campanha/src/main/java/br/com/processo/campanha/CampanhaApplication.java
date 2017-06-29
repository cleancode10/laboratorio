package br.com.processo.campanha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger 
public class CampanhaApplication {
	
	@Autowired
	private SpringSwaggerConfig swaggerConfig;


	public static void main(String[] args) {
		SpringApplication.run(CampanhaApplication.class, args);
	}
	
	@Bean
    public SwaggerSpringMvcPlugin groupOnePlugin() {
       return new SwaggerSpringMvcPlugin(swaggerConfig)
           .apiInfo(apiInfo()) 
           .includePatterns("/campanha.*?") 
           .swaggerGroup("admin");
    }
     
    private ApiInfo apiInfo() {
       ApiInfo apiInfo = new ApiInfo( 
             "Swagger With Spring Boot",
             "This is a simple application to demonstrate how to work with Swagger in Spring Boot project!",
             "Free to use and mess around",
             "developer71@gmail.com",
             "Open Licence",
             "developer71@gmail.com"
       );
       return apiInfo;
    }
	
}
