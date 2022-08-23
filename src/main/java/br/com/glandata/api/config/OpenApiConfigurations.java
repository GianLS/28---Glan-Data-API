package br.com.glandata.api.config;

import javax.servlet.http.HttpServletRequest;

import org.springdoc.core.SpringDocUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import br.com.glandata.api.validation.ErroDeFormularioDto;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfigurations {

	@Value("${application-description}")
	private String appDescription;

	@Value("${application-version}")
	private String appVersion;

	@Bean
	OpenAPI customOpenAPI() {
		SpringDocUtils.getConfig().addResponseTypeToIgnore(Pageable.class);
		SpringDocUtils.getConfig().addResponseTypeToIgnore(Sort.class);
		SpringDocUtils.getConfig().addResponseTypeToIgnore(ErroDeFormularioDto.class);
		SpringDocUtils.getConfig().addResponseTypeToIgnore(HttpServletRequest.class);

		return new OpenAPI()
				.components(new Components().addSecuritySchemes("Authorization",
						new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
				.info(new Info().title("Produtos API").description(appDescription).version(appVersion));
	}
	
	/*
	 * @Bean GroupedOpenApi AuthorizationApi() { return
	 * GroupedOpenApi.builder().group("Authorization API").pathsToMatch("/auth/**").
	 * build(); }
	 * 
	 * @Bean GroupedOpenApi ProdutoApi() { return
	 * GroupedOpenApi.builder().group("Produtos API").pathsToMatch("/produtos/**").
	 * build(); }
	 * 
	 * @Bean GroupedOpenApi CategoriaApi() { return
	 * GroupedOpenApi.builder().group("Categorias API").pathsToMatch(
	 * "/categorias/**").build(); }
	 * 
	 * @Bean GroupedOpenApi ClienteApi() { return
	 * GroupedOpenApi.builder().group("Clientes API").pathsToMatch("/clientes/**").
	 * build(); }
	 */
}
