package se.experis.com.mefit.config;


import lombok.RequiredArgsConstructor;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
        private final JwtAuthConverter jwtAuthConverter;

        @Bean
        CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowCredentials(false);
                configuration.setAllowedOrigins(Arrays.asList("*"));
                configuration.setAllowedMethods(Arrays.asList("*"));
                configuration.setAllowedHeaders(Arrays.asList("*"));
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);
                return source;
        }

        /*
         * @Bean marks the method as a Spring bean that will be managed by the Spring
         * container.
         * Configure the security filter chain.
         * A series of filters that enforce security rules for incoming requests.
         */
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

                /*
                 * Convenience for dev to quickly get an application up and running!
                 * Compromises on important security aspects.
                 * In a other environments, e.g. production set the proper policies for CORS and
                 * enable CSRF.
                 */
                http
                                .cors(cors -> cors.configurationSource(corsConfigurationSource()));

                /*
                 * Set stateless session creation policy, suitable for RESTful APIs.
                 * Each request is treated independently without maintaining session state.
                 */
                http
                                .sessionManagement((session) -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

                // Configure request authorization rules.
                http
                                .authorizeHttpRequests((authz) -> authz
                                                // Allow unauthenticated access to the specified path.
                                                // Requires users to have the "ADMIN" role to access the specified path.
                                                // .requestMatchers("/api/v1/resources/admin/exercises").hasRole("Admin")
                                                // .requestMatchers("/swagger-ui/index.html#/")
                                                // .permitAll()
                                                // .requestMatchers("/api/v1/users")
                                                // .permitAll()
                                                // .requestMatchers("/api/v1/exercises").permitAll()
                                                // Require authentication for any other request (maps to
                                                // /api/v1/resources/restricted).
                                                .requestMatchers(HttpMethod.POST, "/exercises").hasRole("Contributor")
                                                .requestMatchers(HttpMethod.POST, "/programs").hasRole("Contributor")
                                                .requestMatchers(HttpMethod.GET, "/programs").hasRole("Contributor")
                                                .requestMatchers(request -> request.getRequestURI().startsWith("/swagger")).permitAll()
                                                .requestMatchers(request -> request.getRequestURI().startsWith("/v3")).permitAll()

                                                .anyRequest().authenticated()
                                                )
                                                
                                                //.anyRequest().permitAll())
                                // Configure the OAuth2 resource server settings for handling JWT-based
                                // authentication.
                                .oauth2ResourceServer((oauth2) -> oauth2
                                                // Configure JWT-based authentication and sets a custom JWT
                                                // authentication
                                                // converter.
                                                .jwt(jwt -> jwt
                                                                .jwtAuthenticationConverter(jwtAuthConverter)));

                /*
                 * Build and return the configured `HttpSecurity` object.
                 * Represents the security configuration for the application.
                 */
                return http.build();
        }
}
