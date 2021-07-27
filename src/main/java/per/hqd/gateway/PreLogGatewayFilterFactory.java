package per.hqd.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

//必须以GatewayFilterFactory结尾
@Slf4j
@Component
public class PreLogGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {
    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return ((exchange, chain) -> {
            log.info("请求进来了。。。{}{}", config.getName(), config.getValue());// 分别拿到preLog配置中的a，b
            ServerHttpRequest modifyRequest = exchange.getRequest().mutate().build();// 可以修改request，这里没有做修改
            ServerWebExchange modifyExchange = exchange.mutate().request(modifyRequest).build();// 可以修改exchange
            return chain.filter(modifyExchange);// 交给下一个过滤器
        });
    }
}
