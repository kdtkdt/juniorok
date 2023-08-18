package com.juniorok.juniorok.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.juniorok.juniorok.domain.User;
import com.juniorok.juniorok.dto.AuthUser;
import com.juniorok.juniorok.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@RequiredArgsConstructor
@Service
public class UserAuthService extends DefaultOAuth2UserService implements UserDetailsService {

    private static final String GITHUB_NAME_ATTRIBUTE_KEY = "login";

    private final RestTemplate restTemplate;

    private final UserRepository userRepository;

    /**
     * Remember Me 서비스 이용 시 사용자이름을 받아서 사용자 확인 후 권한이 부여된 사용자 객체를 반환합니다.
     * @param username the username identifying the user whose data is required.
     * @return 권한을 부여받은 사용자 객체를 반환합니다.
     * @throws UsernameNotFoundException
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByName(username);
        return new AuthUser(List.of(new SimpleGrantedAuthority(getUserRole(user))),
                Map.of("username", username),
                "username", user);
    }

    private User findUserByName(String username) {
        return userRepository.findByName(username).orElseThrow(() ->
                new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }

    private String getUserRole(User user) {
        if (user.isAdmin())
            return "ROLE_ADMIN";
        else if (user.isWriter())
            return "ROLE_WRITER";
        else
            return "ROLE_USER";
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Optional<Object> email = Optional.ofNullable(oAuth2User.getAttributes().get("email"));
        if (email.isEmpty())
            email = Optional.of(getUserEmail(userRequest));
        Map<String, Object> attributes = new LinkedHashMap<>(oAuth2User.getAttributes());
        attributes.put("email", email.get());
        saveUser(attributes);
        return new DefaultOAuth2User(oAuth2User.getAuthorities(), attributes, GITHUB_NAME_ATTRIBUTE_KEY);
    }

    private String getUserEmail(OAuth2UserRequest userRequest) {
        return getGithubUserEmail(userRequest);
    }

    private String getGithubUserEmail(OAuth2UserRequest userRequest) {
        ResponseEntity<String> response = requestGithubUserEmails(userRequest);
        JsonNode jsonNode = parseJson(response.getBody());
        return getGithubPrimaryEmail(jsonNode);
    }

    private ResponseEntity<String> requestGithubUserEmails(OAuth2UserRequest userRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(userRequest.getAccessToken().getTokenValue());
        HttpEntity<String> request = new HttpEntity<>(headers);

        String requestUrl = UriComponentsBuilder.fromHttpUrl("https://api.github.com/user/emails").toUriString();

        return restTemplate.exchange(requestUrl, HttpMethod.GET, request, String.class);
    }

    private JsonNode parseJson(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readTree(json);
        } catch (Exception e) {
            return null;
        }
    }

    private String getGithubPrimaryEmail(JsonNode jsonNode) {
        AtomicReference<Optional<String>> email = new AtomicReference<>(Optional.empty());

        jsonNode.elements().forEachRemaining(node -> {
            if (node.get("primary").asBoolean()) {
                email.set(Optional.of(node.get("email").asText()));
            }
        });
        return email.get().orElse("");
    }

    private void saveUser(Map<String, Object> attributes) {
        userRepository.save(User.builder()
                .email(attributes.get("email").toString())
                .nickname(attributes.get(GITHUB_NAME_ATTRIBUTE_KEY).toString())
                .build());
    }
}
