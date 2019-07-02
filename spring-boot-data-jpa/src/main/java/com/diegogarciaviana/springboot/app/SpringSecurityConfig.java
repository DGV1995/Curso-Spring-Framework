package com.diegogarciaviana.springboot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	// Registramos nuestro password encoder BCrypt como componente Spring
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// Registrar usuarios
	@Autowired // Inyectamos el AuthenticationManagerBuilder
	public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {
		
		// Inyectamos el password encoder registrado con @Bean
		PasswordEncoder encoder = passwordEncoder();
		
		// Configuramos la forma en que se va a encriptar la contraseña
		UserBuilder users = User.builder().passwordEncoder(password -> encoder.encode(password));
		
		// Configuramos el builder en memoria y creamos dos usuarios
		builder.inMemoryAuthentication()
										.withUser(users.username("admin").password("12345").roles("ADMIN", "USER"))
										.withUser(users.username("diegogv95").password("diego").roles("USER"));
		
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		// Asignar rutas
		http.authorizeRequests()
						.antMatchers("/", "/css/**", "/js/**", "/images/**", "/listar").permitAll() // Páginas sin autenticación
						.antMatchers("/ver/**").hasAnyRole("USER")
						.antMatchers("/uploads/**").hasAnyRole("USER")
						.antMatchers("/form/**").hasAnyRole("ADMIN")
						.antMatchers("/eliminar/**").hasAnyRole("ADMIN")
						.antMatchers("/factura/**").hasAnyRole("ADMIN")
						.anyRequest().authenticated();
		
	}

}
