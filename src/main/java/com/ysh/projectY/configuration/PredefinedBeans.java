package com.ysh.projectY.configuration;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import org.apache.tomcat.util.http.Rfc6265CookieProcessor;
import org.apache.tomcat.util.http.SameSiteCookies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Component
public class PredefinedBeans {

    @Value("${projectY.api-base-path}")
    private String apiBasePath;

    @Value("${projectY.allowed-origins}")
    private String allowedOrigins;

    @Value("${server.ssl.enabled}") //生产环境关闭SSL后, 同时关闭
    private boolean sslEnabled;

    @Value("${projectY.paypal-isSandbox}")
    private boolean isSandbox;

    //从tomcat容器向springmvc容器传递session事件
    @Bean
    HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    //解决SameSite跨域问题
    @Bean
    public TomcatContextCustomizer sameSiteCookiesConfig() {
        return context -> {
            final Rfc6265CookieProcessor cookieProcessor = new Rfc6265CookieProcessor();
            // 设置Cookie的SameSite
            if (sslEnabled) {
                cookieProcessor.setSameSiteCookies(SameSiteCookies.NONE.getValue());
            }
            context.setCookieProcessor(cookieProcessor);
        };
    }

    //添加spring-session-data-redis后, 解决SameSite跨域问题
//    @Bean
//    public CookieSerializer httpSessionIdResolver() {
//        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
//        if (sslEnabled) {
//            cookieSerializer.setSameSite(SameSiteCookies.NONE.getValue());
//        }
//        cookieSerializer.setCookieName("PHPSESSID");
//        return cookieSerializer;
//    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin(allowedOrigins);
        configuration.addAllowedMethod("*");//修改为添加而不是设置
        configuration.addAllowedHeader("*");//这里很重要，起码需要允许 Access-Control-Allow-Origin
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(1800L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(apiBasePath + "/**", configuration);
        return source;
    }

    @Bean
    PayPalHttpClient getPayPalHttpClient()
    {
        if(isSandbox)
        {
            return new PayPalHttpClient(new PayPalEnvironment.Sandbox(
                    "AYsnjLgEmwy2RIhy0QdO335NCySWCFKnxecxVmVhTVUxLE8hdw_ZNs1LvGD5sH61BErt2JxRuTc7I9Pn",
                    "EPG8TJiiiHlA5wJOjqM_4AG0ddWeDPupGKdGWpONfcGRwY2auStDXBTbJjbT2Hi2Bzfn9ZOD1lmBrTbJ"));
        }
        return new PayPalHttpClient(new PayPalEnvironment.Live(
                "AViXcCKHnqll0J1Lh6mZNFeZ626CepBfscyBpuhV3jed0e8ZCvcMzP4n10eB2qx_gKtSfoZYxMOQgb1y",
                "EC2bYzGx99Ap7ibkO1_zjzSmm_spTUNnBOq95eDpq6LICZrXCEFGhM65Ioum1JSvrPMNOdjL9rgGN3Nk"));
    }
}
