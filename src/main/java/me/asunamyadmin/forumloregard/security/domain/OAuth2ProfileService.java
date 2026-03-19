package me.asunamyadmin.forumloregard.security.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.asunamyadmin.forumloregard.forum_profile.data.ProfileEntity;
import me.asunamyadmin.forumloregard.forum_profile.data.ProfileRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuth2ProfileService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final ProfileRepository profileRepository;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = new DefaultOAuth2UserService().loadUser(userRequest);
        log.info("Attributes: {}", oAuth2User.getAttributes());
        ProfileEntity profile = createProfile(oAuth2User);
        return new DefaultOAuth2User(
                Set.of(profile.getRole().getSimpleGrantedAuthority()),
                oAuth2User.getAttributes(),
                "sub"
        );
    }

    public ProfileEntity getOrCreateProfile(String username) {
        return profileRepository.findByUsername(username).orElseGet(() -> {
            ProfileEntity profile = new ProfileEntity();
            profile.setUsername(username);
            return profileRepository.save(profile);
        });
    }

    private ProfileEntity createProfile(OAuth2User user) {
        String username = user.getAttribute("sub");
        return profileRepository.findByUsername(username).orElseGet(() -> {
            ProfileEntity profile = new ProfileEntity();
            profile.setUsername(username);
            return profileRepository.save(profile);
        });
    }
}
