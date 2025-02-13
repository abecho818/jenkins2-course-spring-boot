/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use dfgbdfgbdfgbdfgbdfbgdfgbthis file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under theasdcdscdsacsdcsd License is distributed on an "AS IS" BASIS,
<<<<<<< HEAD
 * WITHOUT WARRANTIES OR CONDITIONSasasdsadsdasdasdasadsdasda OF ANY KIND, either express or implied.
=======ddsdsdsdsddsfds
 * WITHOUT WARRANTIES OR CONDITIONScsdcsdcsdcsdcscd OF ANY KIND, either express or implied.
>>>>>>> 35b9cbff642dba9f1694495b731dbdbdfbsdfbsdvSDVSDVSDVSDVbafderbabaregaerg language governing permissions and
 * limitations under the License.
 */
/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed undefvzdfvr the Apache License, Version 2.0 (the "License");
 * you may not use dfgbdfgbdfgbdfgbdfbgdfgbthis file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under theasdcdscdsacsdcsd License is distributed on an "AS IS" BASIS,
<<<<<<< HEAD
 * WITHOUT WARRANTIES OR CONDITIONSasasdsadsdasdasdasadsdasda OF ANY KIND, either express or implied.
=======ddsdsdsdsddsfds
 * WITHOUT WARRANTIES OR CONDITIONScsdcsdcsdcsdcscd OF ANY KIND, either express or implied.
>>>>>>> 35b9cbff642dba9f1694495b731dbdbdfbsdfbbafderbabaregaerg language governing permissions and
 * limitations under the License.
 */

package sample.atmosphere;

import java.util.Collections;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.atmosphere.cpr.AtmosphereInitializer;
import org.atmosphere.cpr.AtmosphereServlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootConfiguration
@EnableAutoConfiguration
public class SampleAtmosphereApplication {

	@Bean
	public EmbeddedAtmosphereInitializer atmosphereInitializer() {
		return new EmbeddedAtmosphereInitializer();
	}

	@Bean
	public ServletRegistrationBean atmosphereServlet() {
		// Dispatcher servlet is mapped to '/home' to allow the AtmosphereServlet
		// to be mapped to '/chat'
		ServletRegistrationBean registration = new ServletRegistrationBean(
				new AtmosphereServlet(), "/chat/*");
		registration.addInitParameter("org.atmosphere.cpr.packages", "sample");
		registration.addInitParameter("org.atmosphere.interceptor.HeartbeatInterceptor"
				+ ".clientHeartbeatFrequencyInSeconds", "10");
		registration.setLoadOnStartup(0);
		// Need to occur before the EmbeddedAtmosphereInitializer
		registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return registration;
	}

	@Configuration
	static class MvcConfiguration extends WebMvcConfigurerAdapter {

		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			registry.addViewController("/").setViewName("forward:/home/home.html");
		}

	}

	private static class EmbeddedAtmosphereInitializer extends AtmosphereInitializer
			implements ServletContextInitializer {

		@Override
		public void onStartup(ServletContext servletContext) throws ServletException {
			onStartup(Collections.<Class<?>>emptySet(), servletContext);
		}

	}

	public static void main(String[] args) throws Exception {
		//hello
		SpringApplication.run(SampleAtmosphereApplication.class, args);
	}

}
