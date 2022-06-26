package marjorieTeu.barkpointment.controllers;

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
	
	@GetMapping("/faq")
	public String goFaq() {
		return "faq";
	}
	

	@GetMapping("/profile")
	public String goProfile() {
		return "secured/profile";
	}
	

	@GetMapping("/pets")
	public String goPets() {
		return "secured/pets";
	}

	@GetMapping("/petsAdd")
	public String goPetsAdd() {
		return "secured/petsAdd";
	}


	
	@GetMapping("/visits")
	public String goVisits() {
		return "secured/visits";
	}
	
	@GetMapping("/aptAdd")
	public String goAptAdd() {
		return "secured/aptAdd";
	}
	
	
	@GetMapping("/admin")
	public String goAdmin() {
		return "secured/admin/admin";
	}
}
