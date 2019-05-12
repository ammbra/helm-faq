package com.example.faq.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.faq.DatabaseConfig;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = {DatabaseConfig.class})
@ActiveProfiles("test")
public class AnswerControlerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private AnswerController controller;
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void init() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

	}

	@Test
	public void getAllAnswers() throws Exception {
		mvc.perform(get("/answers").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}


}
