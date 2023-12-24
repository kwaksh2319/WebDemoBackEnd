package kr.co.kshproject.webDemo;

import kr.co.kshproject.webDemo.Common.GlobalHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/") //정적리소스가 위치하에 경로 지정
                .resourceChain(true) //리소스 체인 활성화
                .addResolver(new PathResourceResolver() {
                                @Override
                                protected Resource getResource(String resourcePath, Resource location) throws IOException {
                                    Resource requestedResource = location.createRelative(resourcePath);
                                    return requestedResource.exists() && requestedResource.isReadable() ? requestedResource : new ClassPathResource("/static/index.html");
                                }
                });
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //index.html 포워딩
        registry.addViewController("/").setViewName("forward:/index.html");
        //뷰 컨트롤러 우선순위 가장 높게
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new GlobalHandlerInterceptor());
    }
}
