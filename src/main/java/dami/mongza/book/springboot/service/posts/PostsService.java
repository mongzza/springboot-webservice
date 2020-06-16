package dami.mongza.book.springboot.service.posts;

import dami.mongza.book.springboot.domain.posts.Posts;
import dami.mongza.book.springboot.domain.posts.PostsRepository;
import dami.mongza.book.springboot.web.dto.PostsListResponseDto;
import dami.mongza.book.springboot.web.dto.PostsResponseDto;
import dami.mongza.book.springboot.web.dto.PostsSaveRequestDto;
import dami.mongza.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
	private final PostsRepository postsRepository;

	@Transactional
	public Long save(PostsSaveRequestDto requestDto) {
		return postsRepository.save(requestDto.toEntity()).getId();
	}

	@Transactional
	public Long update(Long id, PostsUpdateRequestDto requestDto) {
		// 영속성 컨텍스트 특징 확인 가능
		// JPA의 엔티티 매니저가 활성화된 상태에서 트랜잭션 안에서 데이터를 가져오면 영속성 컨텍스트 유지된 상태
		// 이 때, Entity 객체의 값을 변경하면 트랜잭션이 종료될 때 테이블에 변경 값 반영 (더티 체킹)
		Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
		posts.update(requestDto.getTitle(), requestDto.getContent());
		return id;
	}

	public PostsResponseDto findById(Long id) {
		Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
		return new PostsResponseDto(entity);
	}

	/**
	 * postsRepository.findAllDesc()로 받아온 Posts의 Stream을 map을 통해 PostsListResponseDto로 변환 후 List로 반환하는 메소드
	 * @return 전체 posts 목록
	 */
	@Transactional(readOnly = true)
	public List<PostsListResponseDto> findAllDesc() {
		return postsRepository.findAllDesc().stream()
				.map(PostsListResponseDto::new)  // == .map(posts -> new PostsListResponseDto(posts))
				.collect(Collectors.toList());
	}

	@Transactional
	public void delete(Long id) {
		Posts posts = postsRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
		postsRepository.delete(posts);
	}
}
