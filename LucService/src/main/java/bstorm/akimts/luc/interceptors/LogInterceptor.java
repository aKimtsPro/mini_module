package bstorm.akimts.luc.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LogInterceptor implements ClientHttpRequestInterceptor {

    Logger log = LoggerFactory.getLogger(LogInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        log.info( String.format("%s - %s", request.getMethod(), request.getURI()) );
        return execution.execute(request,body);
    }
}
