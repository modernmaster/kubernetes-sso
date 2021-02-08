package com.baeldung.client.spring;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

@Component
public class CustomAccessTokenResponseConverter implements Converter<Map<String, String>, OAuth2AccessTokenResponse> {
    private static final Set<String> TOKEN_RESPONSE_PARAMETER_NAMES = new HashSet(Arrays.asList("access_token", "expires_in", "refresh_token", "scope", "token_type"));

    @Override
    public OAuth2AccessTokenResponse convert(Map<String, String> tokenResponseParameters) {
        String accessToken = tokenResponseParameters.get("access_token");
        OAuth2AccessToken.TokenType accessTokenType = OAuth2AccessToken.TokenType.BEARER;
        long expiresIn = 0L;
        if (tokenResponseParameters.containsKey("expires_in")) {
            try {
                expiresIn = Long.parseLong(tokenResponseParameters.get("expires_in"));
            } catch (NumberFormatException var11) {
            }
        }

        Set<String> scopes = Collections.emptySet();
        String refreshToken;
        if (tokenResponseParameters.containsKey("scope")) {
            refreshToken = tokenResponseParameters.get("scope");
            scopes = new HashSet(Arrays.asList(StringUtils.delimitedListToStringArray(refreshToken, " ")));
        }

        refreshToken = tokenResponseParameters.get("refresh_token");
        Map<String, Object> additionalParameters = new LinkedHashMap();
        Iterator var9 = tokenResponseParameters.entrySet().iterator();

        while (var9.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry) var9.next();
            if (!TOKEN_RESPONSE_PARAMETER_NAMES.contains(entry.getKey())) {
                additionalParameters.put(entry.getKey(), entry.getValue());
            }
        }

        return OAuth2AccessTokenResponse.withToken(accessToken).tokenType(accessTokenType).expiresIn(expiresIn).scopes(scopes).refreshToken(refreshToken).additionalParameters(additionalParameters).build();

    }
}
