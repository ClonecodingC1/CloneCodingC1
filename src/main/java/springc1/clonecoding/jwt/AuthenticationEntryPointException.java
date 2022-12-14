package springc1.clonecoding.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import springc1.clonecoding.controller.response.ResponseDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;




@Component
public class AuthenticationEntryPointException implements
        AuthenticationEntryPoint {

  private ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
                       AuthenticationException authException) throws IOException {
    String result = objectMapper.writeValueAsString(ResponseDto.fail("INVALID_TOKEN", "Token이 유효하지 않습니다!"));
    response.setContentType("application/json");
    response.setCharacterEncoding("utf-8");
    response.getWriter().write(result);
  }
}
