package ru.itis.semestrwork_spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import ru.itis.semestrwork_spring.models.User;
import ru.itis.semestrwork_spring.repositories.UsersRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
//TODO Надо переделать
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String email = oAuth2User.getAttribute("email");


        User user = usersRepository.findByEmail(email).get();
        Set<GrantedAuthority> authorities = new HashSet<>(oAuth2User.getAuthorities());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));


        return new DefaultOAuth2User(
                authorities,
                oAuth2User.getAttributes(),
                "email"
        );

    }
}
