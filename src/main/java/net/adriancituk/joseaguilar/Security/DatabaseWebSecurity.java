package net.adriancituk.joseaguilar.Security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity {
	@Bean
	  public UserDetailsManager users(DataSource datasource) {
		   JdbcUserDetailsManager users= new  JdbcUserDetailsManager (datasource);
		   users.setUsersByUsernameQuery("select username, password, estatus from Usuarios where username=?");
		   users.setAuthoritiesByUsernameQuery("select u.username, p.perfil from UsuarioPerfil up " +
		   "inner join Usuarios u on u.id = up.idUsuario " +
		   "inner join Perfiles p on p.id = up.idPerfil " +
		   "where u.username = ?");
             return users;
	}
	@Bean
	public SecurityFilterChain filterchain(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				// Los recursos estáticos no requieren autenticación
				.requestMatchers("/bootstrap/**", "/images/**", "/tinymce/**", "/logos/**").permitAll()
				// Las vistas públicas no requieren autenticación
				.requestMatchers("/indexPaginate", "/signup","/ver/**").permitAll()
				.requestMatchers("/productos/**").hasAnyAuthority("Administrador")
				

				.anyRequest().authenticated();
		http.formLogin(form -> form
                .defaultSuccessUrl("/indexPaginate") // Redirección después del inicio de sesión exitoso
                .permitAll());
		return http.build();
	}
}