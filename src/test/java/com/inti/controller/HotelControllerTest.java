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

import com.inti.models.Hotel;
import com.inti.repository.IHotelRepository;

@WebMvcTest(controllers = HotelController.class)
public class HotelControllerTest {
	
	@Autowired
	private MockMvc mock;
	
	@MockBean
	private IHotelRepository icr;
	
	@Test
	public void saveHotel() throws Exception
	{
		mock.perform(get("/creerHotel")) 
		.andExpect(status().isOk());
//		.andDo(print());
	}

	
	@Test
	public void listeHotel() throws Exception
	{
		mock.perform(get("/listeHotel"))
		.andExpect(status().isOk());
//		.andDo(print());
	}
	
	@Test
	public void deleteHotel() throws Exception
	{
		mock.perform(get("/deleteHotel/1"))
		.andExpect(status().is3xxRedirection())  
		.andExpect(redirectedUrl("/listeHotel"));
//		.andDo(print());
	}
	
	@Test
	public void saveHotelPost() throws Exception
	{
		mock.perform(post("/creerHotel").sessionAttr("hotel", new Hotel("Big Palace", 4)))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/listeHotel"))
		.andDo(print());
	}
	
	@Test
	public void updateHotelPost() throws Exception
	{
		mock.perform(post("/modifierHotel/1").sessionAttr("client", new Hotel("Small Palace", 1)))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/listeHotel"))
		.andDo(print());
	}

}
