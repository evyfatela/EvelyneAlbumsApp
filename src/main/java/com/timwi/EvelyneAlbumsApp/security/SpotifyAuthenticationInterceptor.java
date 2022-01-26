package com.timwi.EvelyneAlbumsApp.security;

import com.timwi.EvelyneAlbumsApp.domain.spotify.Token;
import com.timwi.EvelyneAlbumsApp.properties.SpotifyProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Optional;

import static com.timwi.EvelyneAlbumsApp.constant.SpotifyConstant.*;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class SpotifyAuthenticationInterceptor implements ClientHttpRequestInterceptor {

    private String token;
    private LocalDateTime tokenEndTime;

    private final SpotifyProperties spotifyProperties;


    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        var headers = request.getHeaders();
        if (!headers.containsKey(HttpHeaders.AUTHORIZATION)) {
            headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + getToken());
        }
        return execution.execute(request, body);
    }

    private String getToken() {
        if (null == token || isTokenExpired()) {
            log.info("Get token from spotify");
            token = generateToken();
        }
        return token;
    }

    private String generateToken() {
        log.debug("Get spotify token");
        RestTemplate restTemplate = new RestTemplate();
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder()
                .encodeToString(spotifyProperties.getClientIdAndSecret().getBytes()));

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(GRANT_TYPE, CLIENT_CREDENTIALS);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        Token result =
                restTemplate.postForObject(SPOTIFY_ACCOUNTS_URI + TOKEN, request, Token.class);

        setTokenEndTimeWithMargin(result);

        return Optional.ofNullable(result).map(Token::getAccessToken).orElse("");
    }

    private boolean isTokenExpired() {
        LocalDateTime now = LocalDateTime.now();
        long secondBeforeTokenExpiration = ChronoUnit.SECONDS.between(now, tokenEndTime);
        boolean istokenExpired = secondBeforeTokenExpiration > 0;
        if (istokenExpired) {
            log.debug("Spotify token expired");
        }
        return istokenExpired;
    }

    private void setTokenEndTimeWithMargin(Token result) {
        tokenEndTime = LocalDateTime.now().plusSeconds(
                Optional.ofNullable(result).map(Token::getExpiresIn)
                        .map(Long::valueOf).map(aLong -> aLong - 10).orElse(0L));
        log.debug("Spotify token will expire : " + tokenEndTime);
    }
}
