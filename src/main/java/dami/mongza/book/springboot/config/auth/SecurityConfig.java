package dami.mongza.book.springboot.config.auth;

import dami.mongza.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 스프링 시큐리티 설정 클래스
 */
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final CustomOAuth2UserService customOAuth2UserService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.headers().frameOptions().disable()
				.and()
					.authorizeRequests()
					.antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
					.antMatchers("/api/v1/**").hasRole(Role.USER.name())
					.anyRequest().authenticated()
				.and()
					.logout().logoutSuccessUrl("/")
				.and()
					// OAuth2 설정
					.oauth2Login()
					// OAuth2 로그인 성공 후 사용자 정보 가져올 때 설정
					.userInfoEndpoint()
					// 소셜 로그인 성공 시 진행할 UserService 인터페이스의 구현체 등록
					// 소셜 서비스에서 사용자 정보를 가져온 후 추가로 진행할 기능 명시
					.userService(customOAuth2UserService);

	}

}
