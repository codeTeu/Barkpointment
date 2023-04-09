package ca.marjorieteu.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import ca.marjorieteu.beans.Dog;
import ca.marjorieteu.beans.Owner;
import ca.marjorieteu.database.DatabaseAccess;

@Controller
public class HomeController {
	private DatabaseAccess db;

	public HomeController(DatabaseAccess db) {
		Owner owner1 = new Owner();
		owner1.setFname("John");
		owner1.setLname("Doe");
		owner1.setAddress("222 Weston St");
		owner1.setCity("Toronto");
		owner1.setProvince("ON");
		owner1.setPostalCode("M4S 4A1");
		owner1.setEmail("john@gmail.com");
		owner1.setPhone("6470101010");

		Owner owner2 = new Owner();
		owner2.setFname("Leah");
		owner2.setLname("Smith");
		owner2.setAddress("123 Temp St");
		owner2.setCity("Toronto");
		owner2.setProvince("ON");
		owner2.setPostalCode("M4J 3R1");
		owner2.setEmail("leah@gmail.com");
		owner2.setPhone("647727272");

		db.addOwner(owner1);
		db.addOwner(owner2);

		db.addDog(new Dog("Shiba", "Male", "2023-02-11", "Shiba Inu", 1));
		db.addDog(new Dog("Loki", "Male", "2015-03-03", "Border Collie Inu", 2));
		db.addDog(new Dog("Patch", "Female", "2021-04-11", "Pitbull", 1));

		this.db = db;
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
	public String goPets(Model model) {
		List<Dog> dogList = db.getDogList();
		model.addAttribute("dogList", dogList);
		return "secured/pets";
	}

	@GetMapping("/addPet")
	public String goAddPet(Model model) {
		Dog newDog = new Dog();
		model.addAttribute("newDog", newDog);
		return "secured/addPet";
	}

	@GetMapping("/addPetProcess")
	public String goAddPetProcess(@ModelAttribute Dog newDog) {
		newDog.setOwnerID(1);
		db.addDog(newDog);
		return ("redirect:/pets");
	}

	@GetMapping("/appointments")
	public String goAppts() {
		return "secured/appointments";
	}
}
