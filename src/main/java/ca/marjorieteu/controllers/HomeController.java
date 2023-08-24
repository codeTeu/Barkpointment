package ca.marjorieteu.controllers;

import java.util.Iterator;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

import ca.marjorieteu.beans.Appointment;
import ca.marjorieteu.beans.Breed;
import ca.marjorieteu.beans.Dog;
import ca.marjorieteu.beans.Owner;
import ca.marjorieteu.database.DatabaseAccess;

@Controller
//@RequestMapping(consumes="application/json")
public class HomeController {
	
	//DB access
	private DatabaseAccess db;
	public HomeController(DatabaseAccess db) {
		this.db = db;
	}
	
	//variables
	private ResponseEntity<Breed[]> apiResponseBreeds = null; // for dog breeds from api
	private boolean dataCreated = false;
	private int activeUserID;
	

	/**
	 * adds data in the database
	 */
	public void createData() {
		Owner owner1 = new Owner();
		owner1.setFname("Jane");
		owner1.setLname("Doe");
		owner1.setAddress("222 Weston St");
		owner1.setCity("Toronto");
		owner1.setProvince("ON");
		owner1.setPostalCode("M4S 4A1");
		owner1.setEmail("john@gmail.com");
		owner1.setPhone("6470101010");

		Owner owner2 = new Owner();
		owner2.setFname("John");
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

		db.addAppt(new Appointment("2023-3-12", "09:00:00", 1, 1, "Shiba", "checkup"));
		db.addAppt(new Appointment("2023-4-24", "10:30:00", 1, 3, "Patch", "anti-rabies shot"));
		db.addAppt(new Appointment("2023-6-04", "14:00:00", 2, 2, "Loki", "booster"));
	}

	/**
	 * create data only once before going to the home page
	 * 
	 * @return
	 */
	@GetMapping("/")
	public String goHome(RestTemplate restTemplate) {
		if (dataCreated == false) {
			createData();
			dataCreated = true;
			System.out.println("data created");
		}

		activeUserID=1;
		
		apiResponseBreeds = restTemplate.getForEntity("https://api.thedogapi.com/v1/breeds", Breed[].class); //get dog breeds

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
	public String goBookAppt(Model model) {
		String ownerName = db.getOwner(activeUserID).getFname() + " " + db.getOwner(activeUserID).getLname();
		model.addAttribute("ownerName", ownerName);

		Appointment newAppt = new Appointment();
		newAppt.setOwnerID(activeUserID);
		model.addAttribute("newAppt", newAppt);
		model.addAttribute("dogList", db.getDogListOf(activeUserID));
		return "secured/bookAppt";
	}

	@PostMapping("/bookApptProcess")
	public String goBookApptProcess(@ModelAttribute Appointment newAppt) {
		System.out.println(newAppt.toString());
		
		String dogName = db.getDogByID(newAppt.getDogID()).getName();
		newAppt.setDogName(dogName);
		System.out.println(newAppt.toString());
		db.addAppt(newAppt);
		
		
		return ("redirect:/appointments");
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
		model.addAttribute("dogList", db.getDogListOf(activeUserID));
		return "secured/pets";
	}

	/**
	 * goes to the addPet page
	 * 
	 * @param model dog object, passed to the page to get input data
	 * @return
	 * @throws JsonProcessingException
	 */
	@GetMapping("/addPet")
	public String goAddPet(Model model) {

		model.addAttribute("breed", apiResponseBreeds.getBody());
		model.addAttribute("newDog", new Dog());
		
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
		newDog.setOwnerID(activeUserID);
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
		List<Appointment> apptList = db.getApptListOf(activeUserID);		
	
		model.addAttribute("apptList", apptList);
		
		return "secured/appointments";
	}
}
