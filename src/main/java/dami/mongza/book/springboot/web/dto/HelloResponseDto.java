package dami.mongza.book.springboot.web.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor // final 필드가 포함된 모든 필드의 생성자를 추가
public class HelloResponseDto {
	private final String name;
	private final int amount;
}
