package marjorieTeu.barkpointment.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {

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
	
	@GetMapping("/faq")
	public String goFaq() {
		return "faq";
	}
	

	@GetMapping("/profile")
	public String goProfile() {
		return "profile";
	}
	

	@GetMapping("/pets")
	public String goPets() {
		return "pets";
	}

	@GetMapping("/petsAdd")
	public String goPetsAdd() {
		return "petsAdd";
	}


	@GetMapping("/petsVisits")
	public String goVisits() {
		return "visits";
	}
}
