package dami.mongza.book.springboot.domain.posts;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

	@Autowired
	PostsRepository postsRepository;

	@After
	public void cleanup() {
		postsRepository.deleteAll();
	}

	@Test
	public void testPostsSave() {
		String title = "test post";
		String content = "test content";

		postsRepository.save(Posts.builder()
				.title(title)
				.content(content)
				.author("son.dami80@gmail.com")
				.build());

		List<Posts> postsList = postsRepository.findAll();

		Posts posts = postsList.get(0);
		Assertions.assertThat(posts.getTitle()).isEqualTo(title);
		Assertions.assertThat(posts.getContent()).isEqualTo(content);
	}

	@Test
	public void testSetBaseTimeEntity() {
		LocalDateTime now = LocalDateTime.of(2020, 5, 6, 0, 0, 0);
		postsRepository.save(Posts.builder()
				.title("title")
				.content("content")
				.author("author")
				.build());

		List<Posts> postsList = postsRepository.findAll();

		Posts posts = postsList.get(0);

		System.out.println(">>>>>>>>>>> CreateDate="+posts.getCreatedDate()+", ModifiedDate="+posts.getModifiedDate());

		Assertions.assertThat(posts.getCreatedDate()).isAfter(now);
		Assertions.assertThat(posts.getModifiedDate()).isAfter(now);
	}
}
