package me.asunamyadmin.forumloregard.config;

import lombok.RequiredArgsConstructor;
import me.asunamyadmin.forumloregard.forum_profile.data.ProfileEntity;
import me.asunamyadmin.forumloregard.security.domain.OAuth2ProfileService;
import me.asunamyadmin.forumloregard.security.ForumRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Set;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final OAuth2ProfileService oAuth2ProfileService;
    private final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/css/**", "/js/**", "/images/**", "/topics/**", "/rules")
                        .permitAll()
                        .requestMatchers("/admin/**", "/category/**")
                        .hasAnyAuthority(
                                ForumRoles.ADMIN.getSimpleGrantedAuthority().getAuthority(),
                                ForumRoles.GAME_MASTER.getSimpleGrantedAuthority().getAuthority(),
                                ForumRoles.SYSTEM.getSimpleGrantedAuthority().getAuthority()
                        )
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("/", true)
                        .userInfoEndpoint(userInfo -> userInfo
                                .oidcUserService(this::oidcUserMapper)
                        )
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                )
                .build();
    }

    private OidcUser oidcUserMapper(OidcUserRequest oidcRequest) {
        try {
            OidcUser oidcUser = new OidcUserService().loadUser(oidcRequest);

            String sub = oidcUser.getAttribute("sub");
            if (sub == null) {
                log.error("OIDC 'sub' attribute is null for user: {}", oidcUser.getName());
                throw new RuntimeException("OIDC 'sub' is null");
            }

            ProfileEntity profile = oAuth2ProfileService.getOrCreateProfile(sub);
            if (profile == null || profile.getRole() == null) {
                log.error("Profile or role is null for OIDC user: {}", oidcUser.getName());
                throw new RuntimeException("Profile/Role is null");
            }

            return new DefaultOidcUser(
                    Set.of(profile.getRole().getSimpleGrantedAuthority()),
                    oidcUser.getIdToken(),
                    oidcUser.getUserInfo()
            );
        } catch (Exception e) {
            log.error("Failed to map OIDC user", e);
            throw new RuntimeException("OIDC user mapping failed", e);
        }
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}