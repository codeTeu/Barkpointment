package ca.marjorieteu.controllers;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import ca.marjorieteu.beans.Dog;
import ca.marjorieteu.database.DatabaseAccess;

@Controller
public class HomeController {
	private DatabaseAccess db;

	public HomeController(DatabaseAccess db) {
		this.db = db;
	}

	@GetMapping("/")
	public String goHome() {
		System.out.println(db.getDogs().get(0).toString());		//test db connection
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
	public String goAddPet(Model model) {
		model.addAttribute("dateToday", LocalDate.now());
		int dogIdTemp = 1;
		int ownerIdTemp = 1;
		model.addAttribute("newDog", new Dog(dogIdTemp, ownerIdTemp)); // test owner id
		// System.out.println("addpet"); //test redirect
		return "secured/addPet";
	}

	@GetMapping("/addPetProcess")
	public String goAddPetProcess(@ModelAttribute Dog newDog) {
		System.out.println(newDog.toString()); // test add
		return ("redirect:/addPet");
	}

	@GetMapping("/appointments")
	public String goAppts() {
		return "secured/appointments";
	}
}
