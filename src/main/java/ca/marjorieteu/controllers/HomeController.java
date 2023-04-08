package ca.marjorieteu.controllers;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import ca.marjorieteu.beans.Dog;

@Controller
public class HomeController {
	private int dogCount = 1;

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
	public String goAddPet(Model model) {
		model.addAttribute("dateToday", LocalDate.now());
		int ownerIdTemp = 1;
		model.addAttribute("newDog", new Dog(dogCount, ownerIdTemp)); // test owner id
		dogCount++;
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
