package com.inw.gatewayserver.filters;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;
@Component
public class FilterUtility {

    public static final String CORRELATION_ID = "inwove-correlation-id";
    public String getCorrelationId(HttpHeaders reqHeaders){
        if (reqHeaders.get(CORRELATION_ID) != null){
            List<String> requestHeaderList = reqHeaders.get(CORRELATION_ID);
            return requestHeaderList.stream().findFirst().get();
        }else{
            return null;
        }
    }

    private ServerWebExchange setRequestHeader(ServerWebExchange exchange, String name, String value) {
        return exchange.mutate().request(exchange.getRequest().mutate().header(name, value).build()).build();
    }
    public ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationId) {
        return this.setRequestHeader(exchange, CORRELATION_ID, correlationId);
    }


}
