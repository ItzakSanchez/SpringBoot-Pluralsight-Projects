package dev.edgaritzak.conference.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dev.edgaritzak.conference.models.Registration;
import jakarta.validation.Valid;

@Controller
public class RegistrationController {
	
	@RequestMapping(path = "/registration", method = RequestMethod.GET)
	public String registration(@ModelAttribute("registration") Registration registration) {
		return "registration";
	}
	
	@RequestMapping(path = "/registration", method = RequestMethod.POST)
	public String processRegistration(@Valid @ModelAttribute("registration") Registration registration,
			BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()){
			System.out.println("An Error Occurred");
			return "/registration";
		}
		System.out.println("NEW REGISTRATION DETECTED, NAME: "+registration.getName());
		return "redirect:/registration";
	}
	
}
