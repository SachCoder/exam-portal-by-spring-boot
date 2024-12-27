package com.configs;

 



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.security.ExamAuthenticationEntryPoint;
import com.security.JwtAuthenticationFilter;

@Configuration
@EnableMethodSecurity
//@EnableWebSecurity
public class SecurityConfigs{
@Autowired
private ExamAuthenticationEntryPoint entrypoint;
//@Autowired
//private LoginSuccessHandler loginSuccessHandler;
	@Autowired
	private JwtAuthenticationFilter jwtFilter;
// 	@Bean
//    CustomUserDetailsService customUserDetailsService() {
//      return new CustomUserDetailsService();
//    }
 

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

      @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
  }

      
//    public DaoAuthenticationProvider authenticationProvider() {
//    	DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//    	authProvider.setUserDetailsService(customUserDetailsService());
//    	authProvider.setPasswordEncoder(passwordEncoder());
//   
//    	System.out.print(authProvider);
//    	 
//		return authProvider;
//    }  
     @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	
        http.authorizeHttpRequests(requests ->
        requests
        .requestMatchers("/register" ,"/deleteByUser" ,"/login" ,"/forgetPass").permitAll()
        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
        
      		  .requestMatchers(HttpMethod.OPTIONS).permitAll()        
              		.anyRequest().authenticated())
        	  .csrf(csrf ->csrf.disable());
        
//        .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer.permitAll()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/login"));
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
 http.exceptionHandling(a->a.authenticationEntryPoint(entrypoint));
 
          
          
            return http.build();
              		 
              		
                 
  }
     

   


}
