package dnd.studyplanner.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final OAuthService oAuthService;
	private final OAuth2SuccessHandler oAuth2SuccessHandler;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.headers().frameOptions().disable()
			.and()
			.authorizeRequests()
			.antMatchers("/index","/css/**","/images/**","/js/**","/h2-console/**", "/profile", "/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.logout()
			.logoutSuccessUrl("/login/success")
			.and()
			.oauth2Login()// OAuth2 로그인 설정 시작
			.successHandler(oAuth2SuccessHandler)
			.userInfoEndpoint()// OAuth2 로그인 성공 이후 사용자 정보를 가져올 때 설정을 저장

			.userService(oAuthService); // OAuth2 로그인 성공 시, 후작업을 진행할 UserService 인터페이스 구현체 등록
	}

}
