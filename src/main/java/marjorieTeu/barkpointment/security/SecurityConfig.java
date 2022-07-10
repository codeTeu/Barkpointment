package marjorieTeu.barkpointment.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Logging loggingAccessDeniedHandler;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	JdbcUserDetailsManager  jdbcUserDetailsManager() throws Exception{
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
		
		jdbcUserDetailsManager.setDataSource(dataSource);
	}
	
	@Autowired
	@Lazy
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	public void configure(HttpSecurity http) throws Exception{
		
		http.authorizeHttpRequests()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/user/**","/**", "/" ).permitAll()
			.and()
			
			.formLogin()
			.defaultSuccessUrl("/index")
			.and()
			
			.logout().invalidateHttpSession(true)
			.clearAuthentication(true);
//			.and()
//			.exceptionHandling()
//			.accessDeniedHandler(accessDeniedHandler);

			http.csrf().disable();
			http.headers().frameOptions().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.withDefaultSchema()
		.passwordEncoder(passwordEncoder)
		.withUser("1")
		.password(passwordEncoder.encode("1")).roles("USER")
		.and()
		.withUser("2")
		.password(passwordEncoder.encode("2")).roles("ADMIN")
		;
		
		auth.inMemoryAuthentication()
		.passwordEncoder(NoOpPasswordEncoder.getInstance())
		.withUser("1").password("1").roles("USER");;
	}
}
