package kr.co.kshproject.webDemo;

import org.springframework.core.io.PathResource;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.resource.PathResourceResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CustomPathResourceResolver extends PathResourceResolver {

    protected MediaType getDefaultMediaType(PathResource resource) {
        String mimeType;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            ServletContext servletContext = request.getServletContext();
            mimeType = servletContext.getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            mimeType = "text/html";
        }
        return (mimeType != null) ? MediaType.valueOf(mimeType) : MediaType.TEXT_HTML;
    }
}
