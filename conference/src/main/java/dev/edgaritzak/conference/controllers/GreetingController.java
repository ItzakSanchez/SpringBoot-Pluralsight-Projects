package dev.edgaritzak.conference.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GreetingController {
	
	@RequestMapping(path ="/greeting", method = RequestMethod.GET)
	public String greeting(Model model) {
		model.addAttribute("message", "Hello Itzak");
		return "greeting";
	}
}
