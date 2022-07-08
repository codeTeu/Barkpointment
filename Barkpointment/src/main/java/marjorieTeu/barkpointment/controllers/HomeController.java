package marjorieTeu.barkpointment.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;

import marjorieTeu.barkpointment.beans.Dog;
import marjorieTeu.barkpointment.beans.Account;
import marjorieTeu.barkpointment.database.DatabaseAccess;

@Controller
public class HomeController {

	// access database through this class
	private DatabaseAccess db;

	// dependency injection, parameter will be injected at runtime
	public HomeController(DatabaseAccess database) {
		this.db = database;
	}

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
	public String goPets(Model model) {
		List<Dog> dogs = db.getDogs();
		model.addAttribute("dogList", dogs);
		return "secured/admin/pets";
	}

	@GetMapping("/accounts")
	public String goAccounts(Model model) {
		List<Account> acctList = db.getAccounts();
		model.addAttribute("acctList", acctList);
		return "secured/admin/accounts";
	}

	@GetMapping("/petsAdd")
	public String goPetsAdd(Model model) {
		model.addAttribute("dog", new Dog());
		return "secured/admin/petsAdd";
	}

	@PostMapping("/addAdog")
	public String addAdog(@ModelAttribute Dog dog) {
		int result = db.addDog(dog);

		System.out.println(result);
		return "redirect:/pets";
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
