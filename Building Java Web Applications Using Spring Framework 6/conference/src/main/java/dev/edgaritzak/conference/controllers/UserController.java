package dev.edgaritzak.conference.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.edgaritzak.conference.models.User;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	@RequestMapping(path = "/getUser", method= RequestMethod.GET)
	public User getUser() {
		User user1 = new User();
		user1.setId(1L);
		user1.setName("Edgar Itzak");
		return user1;
	}
}
