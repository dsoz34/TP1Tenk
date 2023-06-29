package com.inti.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.inti.models.Destination;
import com.inti.repository.IDestinationRepository;

@WebMvcTest(controllers = DestinationController.class)
public class DestinationControllerTest {
	
	@Autowired
	private MockMvc mock;
	
	@MockBean
	private IDestinationRepository icr;
	
	@Test
	public void saveDestination() throws Exception
	{
		mock.perform(get("/creerDestination")) 
		.andExpect(status().isOk());
//		.andDo(print());
	}

	
	@Test
	public void listeDestination() throws Exception
	{
		mock.perform(get("/listeDestination"))
		.andExpect(status().isOk());
//		.andDo(print());
	}
	
	@Test
	public void deleteDestination() throws Exception
	{
		mock.perform(get("/deleteDestination/1"))
		.andExpect(status().is3xxRedirection())  
		.andExpect(redirectedUrl("/listeDestination"));
//		.andDo(print());
	}
	
	@Test
	public void saveDestinationPost() throws Exception
	{
		mock.perform(post("/creerDestination").sessionAttr("destination", new Destination(2222, 33333)))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/listeDestination"))
		.andDo(print());
	}
	
	@Test
	public void updateDestinationPost() throws Exception
	{
		mock.perform(post("/modifierDestination/1").sessionAttr("client", new Destination(5555, 111111)))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/listeDestination"))
		.andDo(print());
	}

}
