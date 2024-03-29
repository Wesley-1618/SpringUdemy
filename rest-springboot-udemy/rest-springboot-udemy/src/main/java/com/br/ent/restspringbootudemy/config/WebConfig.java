package com.br.ent.restspringbootudemy.config;


import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.br.ent.restspringbootudemy.serialization.converter.YamlJackson2HttpMessageConverter;

@Configuration
//@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
	
	private static final MediaType MEDIA_TYPE_YML = MediaType.valueOf("application/x-yaml");
	
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedMethods("GET","POST","PUT","PATCH","DELETE","OPTIONS","HEAD","TRACE","CONNECT");
	}
	
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new YamlJackson2HttpMessageConverter());
	}
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
	    configurer.favorPathExtension(false).
	    	favorParameter(false).
//	    	parameterName("mediaType").
	    	ignoreAcceptHeader(false).
	    	useRegisteredExtensionsOnly(false).
	    	defaultContentType(MediaType.APPLICATION_JSON).
	    	mediaType("json", MediaType.APPLICATION_JSON). 
	    	mediaType("xml", MediaType.APPLICATION_XML).
	    	mediaType("x-yaml", MEDIA_TYPE_YML); 
	}
}
