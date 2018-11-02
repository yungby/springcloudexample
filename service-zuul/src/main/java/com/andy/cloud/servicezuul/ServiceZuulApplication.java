package com.andy.cloud.servicezuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import javax.servlet.http.HttpServletRequest;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class ServiceZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceZuulApplication.class, args);
    }

    @Bean
    public TokenFilter accessFilter() {
        return new TokenFilter();
    }

    public class TokenFilter extends ZuulFilter {

        private final Logger log = LoggerFactory.getLogger(TokenFilter.class);

        @Override
        public String filterType() {
            return "pre"; // 可以在请求被路由之前调用
        }

        @Override
        public int filterOrder() {
            return 0; // filter执行顺序，通过数字指定 ,优先级为0，数字越大，优先级越低
        }

        @Override
        public boolean shouldFilter() {
            return true;// 是否执行该过滤器，此处为true，说明需要过滤
        }

        @Override
        public Object run() {
            RequestContext ctx = RequestContext.getCurrentContext();
            HttpServletRequest request = ctx.getRequest();
            log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
            Object accessToken = request.getParameter("token");
            if (accessToken == null) {
                log.warn("token is empty");
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(401);
                try {
                    ctx.getResponse().getWriter().write("token is empty");
                } catch (Exception e) {
                }
                return null;
            }
            log.info("ok");
            return null;
        }

    }
}
