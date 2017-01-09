package me.jpomykala.rest.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by evelan on 12/24/15.
 */
@Configuration
@ComponentScan(basePackages = "me.jpomykala.service", includeFilters = @ComponentScan.Filter(value = Service.class, type = FilterType.ANNOTATION))
public class ServiceConfig {
}
