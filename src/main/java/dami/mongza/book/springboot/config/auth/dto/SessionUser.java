package dami.mongza.book.springboot.config.auth.dto;

import dami.mongza.book.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

/**
 * 엔티티로 사용하는 User 클래스에는 직렬화를 구현하지 않음
 * 엔티티는 언제 다른 엔티티와 관계가 형성될지 모르며 자식 엔티티를 갖고있어 직렬화 시 성능 이슈 가능성
 * 따라서 직렬화 기능을 가진 세션 Dto 추가
 */
@Getter
public class SessionUser implements Serializable {
	private String name;
	private String email;
	private String picture;

	public SessionUser(User user) {
		this.name = user.getName();
		this.email = user.getEmail();
		this.picture = user.getPicture();
	}
}
