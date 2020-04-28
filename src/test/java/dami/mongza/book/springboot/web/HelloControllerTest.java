package dami.mongza.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void returnHelloTest() throws Exception {
		String hello = "hello";

		mvc.perform(get("/hello"))
				.andExpect(status().isOk())             // 상태(응답 코드) 검증
				.andExpect(content().string(hello));    // 응답 본문 검증
	}

	@Test
	public void returnHelloDtoTest() throws Exception {
		String name = "hello";
		int amount = 1000;

		mvc.perform(get("/hello/dto")
							.param("name", name)
							.param("amount", String.valueOf(1000)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is(name)))
				.andExpect(jsonPath("$.amount", is(amount)));
	}
}
