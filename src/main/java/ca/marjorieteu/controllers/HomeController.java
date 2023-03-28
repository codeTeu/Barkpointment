package ca.marjorieteu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String goHome() {
		return "index";
	}

	@GetMapping("/login")
	public String goLogin() {
		return "login";
	}

	@GetMapping("/register")
	public String goRegister() {
		return "register";
	}

	@GetMapping("/contact")
	public String goContact() {
		return "contact";
	}

	@GetMapping("/bookAppt")
	public String goBookAppt() {
		return "secured/index";
	}

	@GetMapping("/profile")
	public String goProfile() {
		return "secured/profile";
	}

	@GetMapping("/pets")
	public String goPets() {
		return "secured/pets";
	}

	@GetMapping("/addPet")
	public String goAddPet() {
		return "secured/addPet";
	}

	@GetMapping("/appointments")
	public String goAppts() {
		return "secured/appointments";
	}
}
