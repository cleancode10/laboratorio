package br.com.processo.clube;

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
@EnableSwagger //Habilita o Swagger
public class ClubeApplication {
	
	@Autowired
	private SpringSwaggerConfig swaggerConfig;

	public static void main(String[] args) {
		SpringApplication.run(ClubeApplication.class, args);
	}
	
	@Bean
    public SwaggerSpringMvcPlugin groupOnePlugin() {
       return new SwaggerSpringMvcPlugin(swaggerConfig)
            //Adiciona as configurações do Swagger ao SwaggerSpringMvcPlugin 
           .apiInfo(apiInfo()) //Adiciona as propriedades de configuração
           .includePatterns("/clube.*?") //Habilita o Swagger para os nossos 2 endpoints
           .swaggerGroup("admin");
    }
     
    private ApiInfo apiInfo() {
       ApiInfo apiInfo = new ApiInfo( //Configurações de contato, licença etc não nescessáriamente precisa ser definida
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
