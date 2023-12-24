package org.example.config;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
@Configuration
public class ErrorHandlingFilter {

    @Bean
    public ErrorWebExceptionHandler errorWebExceptionHandler() {
        return new CustomErrorWebExceptionHandler();
    }

    private static class CustomErrorWebExceptionHandler implements ErrorWebExceptionHandler {

        @Override
        public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
            if (exchange.getResponse().isCommitted()) {
                return Mono.error(ex);
            }

            exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

            String errorMessage = "{\"error\": \"An internal server error occurred\"}";

            return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(errorMessage.getBytes())));
        }
    }
}