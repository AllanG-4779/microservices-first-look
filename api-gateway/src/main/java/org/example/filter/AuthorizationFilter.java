package org.example.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class AuthorizationFilter extends AbstractGatewayFilterFactory<AuthorizationFilter.Config>{
 private final WebClient.Builder webClientBuilder;
    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
//            check if there is an authorization header
            if(exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                return null;
            }
            return null;
        }
        );
    }

    public static class Config{

    }

}
