package ca.marjorieteu.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import ca.marjorieteu.beans.Appointment;
import ca.marjorieteu.beans.Dog;
import ca.marjorieteu.beans.Owner;
import ca.marjorieteu.database.DatabaseAccess;

@Controller
public class HomeController {

	private DatabaseAccess db;

	private boolean dataCreated = false;

	public HomeController(DatabaseAccess db) {
		this.db = db;
	}

	/**
	 * adds data in the database
	 */
	public void createData() {
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

		db.addAppt(new Appointment("2023-4-12", "09:00:00", 1, 1, "checkup"));
		db.addAppt(new Appointment("2023-1-12", "10:30:00", 1, 2, "anti-rabies shot"));
		db.addAppt(new Appointment("2023-6-04", "14:00:00", 2, 1, "booster"));
	}

	/**
	 * create data only once before going to the home page
	 * 
	 * @return
	 */
	@GetMapping("/")
	public String goHome() {
		if (dataCreated == false) {
			createData();
			dataCreated = true;
			System.out.println("data created");
		}
		return "index";
	}

	/**
	 * goes top the login page
	 * 
	 * @return
	 */
	@GetMapping("/login")
	public String goLogin() {
		return "login";
	}

	/**
	 * goes to the register page
	 * 
	 * @return
	 */
	@GetMapping("/register")
	public String goRegister() {
		return "register";
	}

	/**
	 * goes to the contact page
	 * 
	 * @return
	 */
	@GetMapping("/contact")
	public String goContact() {
		return "contact";
	}

	/**
	 * goes to the book appointment page
	 * 
	 * @return
	 */
	@GetMapping("/bookAppt")
	public String goBookAppt() {
		return "secured/index";
	}

	/**
	 * goes to the users profile page
	 * 
	 * @return
	 */
	@GetMapping("/profile")
	public String goProfile(Model model) {
		model.addAttribute("owner", db.getOwner(1));
		return "secured/profile";
	}

	/**
	 * updates the users data in the db
	 * 
	 * @param newOwnerData owner object with the new inputed data
	 * @return goes back to profile page
	 */
	@GetMapping("/profileUpdate")
	public String goProfileUpdate(@ModelAttribute Owner newOwnerData) {
		// update profile
		int result = db.profileUpdate(newOwnerData);
		System.out.println(result > 0 ? "profile update successfull" : "profile update failed ");
		return "redirect:/profile";
	}

	/**
	 * goes to the pets page
	 * 
	 * @param model dogList, passed to the page to be displayed
	 * @return
	 */
	@GetMapping("/pets")
	public String goPets(Model model) {
		List<Dog> dogList = db.getDogList();
		model.addAttribute("dogList", dogList);
		return "secured/pets";
	}

	/**
	 * goes to the addPet page
	 * 
	 * @param model dog object, passed to the page to get input data
	 * @return
	 */
	@GetMapping("/addPet")
	public String goAddPet(Model model) {
		Dog newDog = new Dog();
		model.addAttribute("newDog", newDog);
		return "secured/addPet";
	}

	/**
	 * adds the new dog in the db
	 * 
	 * @param newDog dog obj with the input data
	 * @return goes back to pet page
	 */
	@GetMapping("/addPetProcess")
	public String goAddPetProcess(@ModelAttribute Dog newDog) {
		newDog.setOwnerID(1);
		db.addDog(newDog);
		return ("redirect:/pets");
	}

	/**
	 * goes to the appointments page
	 * 
	 * @return
	 */
	@GetMapping("/appointments")
	public String goAppts(Model model) {
		List<Appointment> apptList = db.getApptList();
		model.addAttribute("apptList", apptList);
		return "secured/appointments";
	}
}
