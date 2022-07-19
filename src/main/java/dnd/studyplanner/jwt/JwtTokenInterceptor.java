package dnd.studyplanner.jwt;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtTokenInterceptor implements HandlerInterceptor {

	private final JwtService jwtService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws IOException {

		System.out.println("JwtToken 호출");
		String accessToken = jwtService.getJwt();
		System.out.println("AccessToken:" + accessToken);
		String refreshToken = request.getHeader("REFRESH_TOKEN");
		System.out.println("RefreshToken:" + refreshToken);

		if (accessToken != null) {
			if (jwtService.isExpired(accessToken)) {
				response.setStatus(401);
				response.setHeader("ACCESS_TOKEN", accessToken);
				response.setHeader("msg", "ACCESS TOKEN EXPIRED");
				return false;
			} else if (jwtService.isNotValid(accessToken)) {
				response.setStatus(401);
				response.setHeader("ACCESS_TOKEN", accessToken);
				response.setHeader("msg", "INVALID TOKEN");
				return false;
			}
			return true;
		}
		response.setStatus(401);
		response.setHeader("ACCESS_TOKEN", accessToken);
		response.setHeader("REFRESH_TOKEN", refreshToken);
		response.setHeader("msg", "Check the tokens.");
		return false;
	}
}
