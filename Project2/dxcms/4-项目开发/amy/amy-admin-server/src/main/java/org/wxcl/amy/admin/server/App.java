package org.wxcl.amy.admin.server;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 *
 *
 * @author wangxin
 * @create 2018/8/1
 * @since 1.0.0
 */
@SpringBootApplication
@EnableEurekaClient
@EnableAdminServer
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Configuration
    public static class SecuritySecureConfig extends WebSecurityConfigurerAdapter {
        private final String adminContextPath;

        public SecuritySecureConfig(AdminServerProperties adminServerProperties) {
            this.adminContextPath = adminServerProperties.getContextPath();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
            successHandler.setTargetUrlParameter("redirectTo");
            successHandler.setDefaultTargetUrl(adminContextPath + "/");

            http.authorizeRequests()
                    .antMatchers(adminContextPath + "/assets/**").permitAll()
                    .antMatchers(adminContextPath + "/login").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin().loginPage(adminContextPath + "/login").successHandler(successHandler).and()
                    .logout().logoutUrl(adminContextPath + "/logout").and()
                    .httpBasic().and()
                    .csrf()
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                    .ignoringAntMatchers(
                            "/instances",
                            "/actuator/**"
                    );
            // @formatter:on
        }
    }


    //@Profile("insecure")
    //@Configuration
    //public static class SecurityPermitAllConfig extends WebSecurityConfigurerAdapter {
    //    @Override
    //    protected void configure(HttpSecurity http) throws Exception {
    //        http.authorizeRequests().anyRequest().permitAll()//
    //                .and().csrf().disable();
    //    }
    //}
    //
    //@Profile("secure")
    //@Configuration
    //public static class SecuritySecureConfig extends WebSecurityConfigurerAdapter {
    //    private final String adminContextPath;
    //
    //    public SecuritySecureConfig(AdminServerProperties adminServerProperties) {
    //        this.adminContextPath = adminServerProperties.getContextPath();
    //    }
    //
    //    @Override
    //    protected void configure(HttpSecurity http) throws Exception {
    //        // @formatter:off
    //        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
    //        successHandler.setTargetUrlParameter("redirectTo");
    //
    //        http.authorizeRequests()
    //                .antMatchers(adminContextPath + "/assets/**").permitAll()
    //                .antMatchers(adminContextPath + "/login").permitAll()
    //                .anyRequest().authenticated()
    //                .and()
    //                .formLogin().loginPage(adminContextPath + "/login").successHandler(successHandler).and()
    //                .logout().logoutUrl(adminContextPath + "/logout").and()
    //                .httpBasic().and()
    //                .csrf().disable();
    //        // @formatter:on
    //    }
    //}

}
