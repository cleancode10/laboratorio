package br.com.processo.sociotorcedor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger //Habilita o Swagger
public class SocioTorcedorApplication {
	
	@Autowired
	private SpringSwaggerConfig swaggerConfig;
	

	public static void main(String[] args) {
		SpringApplication.run(SocioTorcedorApplication.class, args);
	}
	
	@LoadBalanced
	@Bean
	RestTemplate getRestTemplate() {
		return new RestTemplate();
	}


	@Bean
    public SwaggerSpringMvcPlugin groupOnePlugin() {
       return new SwaggerSpringMvcPlugin(swaggerConfig)
            //Adiciona as configurações do Swagger ao SwaggerSpringMvcPlugin 
           .apiInfo(apiInfo()) //Adiciona as propriedades de configuração
           .includePatterns("/socio.*?") //Habilita o Swagger para os nossos 2 endpoints
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
