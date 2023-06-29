package com.inti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.inti.models.Destination;
import com.inti.repository.IDestinationRepository;

@Controller
public class DestinationController {

	@Autowired
	IDestinationRepository idr;

	@GetMapping("creerDestination")
	public String creerDestination() {
		return "creerDestination";
	}

	@PostMapping("creerDestination")
	public String creerDestination(@ModelAttribute("destination") Destination d) {
		idr.save(d);

		return "redirect:/listeDestination";
	}

	@GetMapping("listeDestination")
	public String listeDestination(Model m) {
		m.addAttribute("listeD", idr.findAll());

		return "listeDestination";
	}

	@GetMapping("modifierDestination/{id}")
	public String modifierDestination(Model m, @PathVariable("id") int id) {
		m.addAttribute("destination", idr.getReferenceById(id));

		return "modifierDestination";
	}

	@PostMapping("modifierDestination/{id}")
	public String modifierDestination(@ModelAttribute("destination") Destination d, @PathVariable("id") int id) { // osef du path?
		idr.save(d);
		//
		return "redirect:/listeDestination";
	}

	@GetMapping("deleteDestination/{id}")
	public String deleteDestination(@PathVariable("id") int id) {
		idr.delete(idr.getReferenceById(id));
		return "redirect:/listeDestination";
	}

}
