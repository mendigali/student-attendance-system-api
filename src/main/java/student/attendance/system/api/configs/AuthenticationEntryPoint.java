package student.attendance.system.api.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.RequiredArgsConstructor;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import student.attendance.system.api.exceptions.APIAuthorizationException;
import student.attendance.system.api.exceptions.APIError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    private static final String CONTENT_TYPE = "application/json";

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authEx) throws IOException {
        APIError apiError = new APIError(new APIAuthorizationException(authEx.getMessage()));
        response.setStatus(Integer.parseInt(apiError.getHttpStatus()));
        response.setContentType(CONTENT_TYPE);

        try {
            ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
                    .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                    .build();

            String jsonResponse = objectMapper.writeValueAsString(apiError);

            response.getWriter().write(jsonResponse);
            response.flushBuffer();
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    public void afterPropertiesSet() {
        setRealmName("student-attendance-system-api");
        super.afterPropertiesSet();
    }
}
