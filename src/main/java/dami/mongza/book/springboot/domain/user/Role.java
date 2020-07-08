package dami.mongza.book.springboot.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum Role {

	// 스프링 시큐리티는 권한 확인 시 접두사로 반드시 ROLE_ 사용
	GUEST("ROLE_GUEST", "손님"),
	USER("ROLE_USER", "일반 사용자");

	private final String key;
	private final String title;
}
