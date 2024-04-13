package com.Busybox.rewards.application.config;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.Busybox.rewards.application.security.JwtAuthenticationEntryPoint;
import com.Busybox.rewards.application.security.JwtAuthenticationFilter;
import com.Busybox.rewards.application.security.UserModel;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Autowired
    UserDetailsService userDetailsService;
    
    @Autowired
    PasswordEncoder passwordEncoder;

//    @Autowired private RoleHierarchy roleHierarchy;
    
//    @Autowired RoleHierarchyImpl roleHierarchyImpl;
    
    @Autowired
    private JwtAuthenticationEntryPoint point;

    @Autowired
    private JwtAuthenticationFilter filter;

    // Define string arrays
    String[] arr = {"CASHIER","SUPER_ADMIN"};
    String[] arr1 = {"SUPER_ADMIN"};
    String[] arr2 = {"ROLE_1"};

    // Create a list of string arrays
    List<String[]> roleList = new ArrayList<>();

    {
        roleList.add(arr);
        roleList.add(arr1);
        roleList.add(arr2);
    }

    String[] arrayList = {"api/**"}; // super admin
    String[] arrayList1 = {"api/customer", "api/customer/hello2"}; // store manager
    String[] arrayList2 = {"api/customer", "api/customer/hello2", "api/customer/hello3"}; // cashier
    List<String[]> apiAccessList = new ArrayList<>();

    {
        apiAccessList.add(arrayList);
        apiAccessList.add(arrayList1);
        apiAccessList.add(arrayList2);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(request -> new CorsConfiguration().applyPermitDefaultValues())
            .headers(headers -> {
                headers.contentSecurityPolicy("default-src 'self' https://localhost:3000");
            })
            .authorizeHttpRequests(requests -> requests
                .requestMatchers("/test").authenticated()
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/auth/login").permitAll()
                .requestMatchers("auth/add/members").permitAll() 
                .requestMatchers("/**").hasAnyAuthority("SUPER_ADMIN","STORE_MANAGER","CASHIER")                 
                
                .anyRequest().authenticated()
            )
            .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    	http
//            .csrf(csrf -> csrf.disable())
//            .cors(request -> new CorsConfiguration().applyPermitDefaultValues())
//            .headers(headers -> {
//                headers.contentSecurityPolicy("default-src 'self' https://localhost:3000");
//            })
//            .authorizeHttpRequests(requests -> {
//                     requests.requestMatchers(arrayList).hasAnyAuthority("SUPER_ADMIN","STORE_MANAGER","CASHIER");
////                     requests.requestMatchers(arrayList2).hasAuthority("ROLE_1");
//                     
//                     UserModel user = new UserModel();
//                     String username = user.getRoleName();
//
//                requests
//                    .requestMatchers("/test").authenticated()
//                    .requestMatchers("/auth/**").permitAll()
//                    .requestMatchers("customer/**").permitAll()
//                    .requestMatchers("auth/getall").hasAuthority("SUPER_ADMIN")
//                    .requestMatchers("auth/getall").hasAuthority("SUPER_ADMIN")
//                    .requestMatchers("/**").hasAuthority("SUPER_ADMIN")
////                    .requestMatchers("/**").hasAuthority("SUPER_ADMIN")
//                    .anyRequest().authenticated();
//            })
//            .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
//            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*"); // Allow requests from any origin
        configuration.addAllowedMethod("*"); // Allow all HTTP methods
        configuration.addAllowedHeader("*"); // Allow all headers
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }
    
    private String getUsernameFromToken() {
    	return this.getUsernameFromToken();
    }
    
    
//    @Bean
//    public RoleHierarchy roleHierarchy() {
//        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
//        String hierarchy = "SUPER_ADMIN > ADMIN \n ADMIN > MANAGER \n MANAGER > CASHIER \n CASHIER > USERS";
//        roleHierarchy.setHierarchy(hierarchy);
//        return roleHierarchy;
//    }
//
//    @Bean
//    public DefaultWebSecurityExpressionHandler customWebSecurityExpressionHandler() {
//        DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
//        expressionHandler.setRoleHierarchy(roleHierarchy());
//        return expressionHandler;
//    }
    
    private List<String> retrieveSuperAdminAPIsFromDatabase() {
        // Replace with your logic to retrieve SUPER_ADMIN APIs from the database
        return List.of("api1", "api2", "api3", "api4", "api5", "api6", "api7", "api8", "api9", "api10");
    }

    private List<String> retrieveCashierAPIsFromDatabase() {
        // Replace with your logic to retrieve CASHIER APIs from the database
        return List.of("api1", "api2", "api3", "api4", "api5");
    }

    private List<String> retrieveRole1APIsFromDatabase() {
        // Replace with your logic to retrieve ROLE_1 APIs from the database
        return List.of("api1", "api2", "api3", "api4", "api5", "api6", "api7", "api8");
    }
    
}

//.requestMatchers("/api/customer").hasAnyAuthority("SUPER_ADMIN")                 
//.requestMatchers("/api/customer","/api/customer/hello3","/api/customer/hello2").hasAnyAuthority("ROLE_1")                 

//.requestMatchers(arrayList).hasAnyAuthority(arr)
//.requestMatchers(arrayList1).hasAnyAuthority(arr1)
//.requestMatchers("/auth/getall").hasAuthority(Role.SUPER_ADMIN.name())                 
//.requestMatchers("/auth/createUser").hasAnyAuthority(SUPER_ADMIN.name())
//.requestMatchers("/api/customer/allcustomer").hasAnyAuthority(Role.CASHIER.name(),Role.SUPER_ADMIN.name())
//.requestMatchers(arrayList).hasAnyAuthority(Role.ROLE_1.name(),Role.ROLE_3.name(),Role.ROLE_2.name())
//.requestMatchers("/api/**").hasAnyAuthority(Role.SUPER_ADMIN.name())
//.requestMatchers("/api/customer/allcustomer").hasAnyAuthority(Role.CASHIER.name())
//.requestMatchers("/api/customer/").hasAnyAuthority(Role.SUPER_ADMIN.name())
//.requestMatchers("/details/**").hasAnyAuthority(Role.SUPER_ADMIN.name())
//.requestMatchers("/master/**").hasAnyAuthority(Role.SUPER_ADMIN.name())
//.requestMatchers("/referral/**").hasAnyAuthority(Role.SUPER_ADMIN.name())
//	@Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http
//        .csrf(csrf -> csrf.disable())
//        .cors(cors -> cors.disable())
//                .authorizeHttpRequests(requests -> requests.
//                        requestMatchers("/test").authenticated()
//                        .requestMatchers("/auth/**").permitAll()
//                        .requestMatchers("/auth/login").permitAll()
//                        .requestMatchers("/auth/getall").permitAll()
////                        .requestMatchers("/api/**").hasAnyAuthority(Role.SUPER_ADMIN.name())
////                        .requestMatchers("/api/wallets/**").hasAnyAuthority(Role.ADMIN.name(),Role.MANGER.name(),Role.SUPER_ADMIN.name())
////                        .requestMatchers("/api/page/**").hasAnyAuthority(Role.SUPER_ADMIN.name())
//                        .requestMatchers("/api/**").hasAnyAuthority(Role.SUPER_ADMIN.name())
//						.requestMatchers("/api/customer/**").permitAll()
//						/* .permitAll() */
//                        .requestMatchers("/details/**").hasAnyAuthority(Role.SUPER_ADMIN.name())
//                        .requestMatchers("/master/**").hasAnyAuthority(Role.SUPER_ADMIN.name())
//                        .requestMatchers("/referral/**").hasAnyAuthority(Role.SUPER_ADMIN.name())
//                        .requestMatchers("auth/add/members").permitAll().anyRequest()
//                        .authenticated()).exceptionHandling(ex -> ex.authenticationEntryPoint(point))
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }
     
	

	
	


	
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .csrf(csrf -> csrf.disable())
//            .cors(request -> new CorsConfiguration().applyPermitDefaultValues())
//            .headers(headers -> {
//                // Configure headers as needed
//            })
//            .authorizeHttpRequests(requests -> {
//                // Configure authorization based on dynamic authorities
//                for (int i = 0; i < arrayList.length; i++) {
//                    String urlPattern = arrayList[i];
//                    String authority = arr[i];
//                    requests
//                        .requestMatchers(urlPattern)
//                        .hasAuthority(authority);
//                }
//                
//                // Add additional authorization rules as needed
//                requests
//                    .requestMatchers("/test").authenticated()
//                    .requestMatchers("/auth/login").permitAll()
//                    .requestMatchers("auth/add/members").permitAll()
//                    .anyRequest().authenticated();
//            })
//            .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
//            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }

	
	
