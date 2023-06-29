package com.inti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.inti.models.Hotel;
import com.inti.repository.IHotelRepository;

@Controller
public class HotelController {
	
	@Autowired
	IHotelRepository ihr;
	
	@GetMapping("creerHotel")
	public String creerHotel()
	{
		return "creerHotel";
	}
	
	@PostMapping("creerHotel")
	public String creerHotel(@ModelAttribute("hotel") Hotel h)
	{		
		ihr.save(h);
		
		return "redirect:/listeHotel";
	}
	////////////////////////////////////////////////////////////
	@GetMapping("listeHotel")
	public String listeHotel(Model m)
	{
		m.addAttribute("listeH", ihr.findAll());
		
		return "listeHotel";
	}
	///////////////////////////////////////////////////////////////
	@GetMapping("modifierHotel/{id}")
	public String modifierHotel(Model m, @PathVariable("id") int id)
	{
		m.addAttribute("hotel", ihr.getReferenceById(id));
		
		return "modifierHotel";
	}
	
	@PostMapping("modifierHotel/{id}")
	public String modifierHotel(@ModelAttribute("hotel") Hotel h, @PathVariable("id") int id)
	{
		ihr.save(h);
		// 
		return "redirect:/listeHotel";
	}
	
	@GetMapping("deleteHotel/{id}")
	public String deleteHotel(@PathVariable("id") int id)
	{
		ihr.delete(ihr.getReferenceById(id));

		return "redirect:/listeHotel";
	}

}
