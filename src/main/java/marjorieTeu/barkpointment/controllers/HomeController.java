package marjorieTeu.barkpointment.controllers;

import java.security.Principal;
import java.sql.Date;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import marjorieTeu.barkpointment.beans.Dog;
import marjorieTeu.barkpointment.beans.Account;
import marjorieTeu.barkpointment.beans.Appointment;
import marjorieTeu.barkpointment.database.DatabaseAccess;


@Controller
public class HomeController {
	
		//for logged in user
		private int acctID=-1;
		private String username="";
		
		
		// access database through this class
		private DatabaseAccess db;

		// dependency injection, parameter will be injected at runtime
		public HomeController(DatabaseAccess database) {
			this.db = database;
		}
		
		
		@Autowired
		private JdbcUserDetailsManager jdbcUserDetailsManager;

		@Autowired
		@Lazy
		private BCryptPasswordEncoder passwordEncoder;
		
		boolean dataCreated = false;
		
		/**
		 * default database data
		 */
		public void createData() {
			Account acctUser = new Account ();
			acctUser.setUsername("user");
			acctUser.setPassword("temp");
			acctUser.setAuthority("ROLE_USER");
			acctUser.setFname("John");
			acctUser.setLname("Doe");
			acctUser.setPhone("6470101010");
			acctUser.setEmail("user@gmail.com");
			
			Account acctAdmin = new Account ();
			acctAdmin.setUsername("admin");
			acctAdmin.setPassword("temp");
			acctAdmin.setAuthority("ROLE_ADMIN");
			acctAdmin.setFname("Emma");
			acctAdmin.setLname("Watson");
			acctAdmin.setPhone("6472020202");
			acctAdmin.setEmail("emma@gmail.com");
			
			db.addAcct(acctUser);
			db.addAcct(acctAdmin);

			db.addDog(new Dog("Shiba", 'F', "Shiba Inu", Date.valueOf("2022-02-11"), 1));
			db.addDog(new Dog("Loki", 'M', "Border Collie Inu", Date.valueOf("2022-03-11"), 2));
			db.addDog(new Dog("Patch", 'F', "Pitbull", Date.valueOf("2022-04-11"), 1));

		}
		
		/**
		 * creates the database once
		 * then goes to the index page
		 * @param model
		 * @param principal
		 * @return
		 */
		@GetMapping("/")
		public String goIndex(Model model, Principal principal) {
			
			if (dataCreated==false) {
				createData();
				dataCreated=true;
				System.out.println("data created");	
			}
			
			
			if (principal != null) {
				username = principal.getName();
				acctID = db.getAccountOf(username).getAcctID();
			}
			
			model.addAttribute("acctID", acctID);
			model.addAttribute("username", username);
			model.addAttribute("sysMessage", "");
			model.addAttribute("alertType", "");

			return "index";
		}


		
		@GetMapping("/login")
		public String goLogin(Model model) {
			model.addAttribute("sysMessage", "");
			model.addAttribute("alertType", "");
			return "login";
		}

		
		
//		@PostMapping("/logout")
//		public String goLogout() {
//			return "index";
//		}
		
		/**
		 * passes an account object to the page for data binding
		 * @param model
		 * @return
		 */
		@GetMapping("/register")
		public String goRegister(Model model) {
			model.addAttribute("account", new Account());
			return "register";
		}

		/**
		 * checks if username already exist in the db 
		 * if username exists, create a system message and go back to the same page
		 * 
		 * it check passed, adds the account
		 * 
		 * @param newAccount account object to be received
		 * @param model
		 * @return to same page
		 */
		@PostMapping("/createUser")
		public String addUser( @ModelAttribute Account newAccount, Model model) {

			boolean userExist =   jdbcUserDetailsManager.userExists(newAccount.getUsername());
			
			if(userExist) {
				model.addAttribute("sysMessage", "Username already exist");
				model.addAttribute("alertType", "danger");
				return "/register";
			}
			
			// check passed
			String encodedPassword = passwordEncoder.encode(newAccount.getPassword());

			List<GrantedAuthority> authorityList = new ArrayList<>();
			authorityList.add(new SimpleGrantedAuthority(newAccount.getAuthority()));

			
			User user = new User(newAccount.getUsername(), encodedPassword, authorityList);

			// create user and authority then copy the pass then create acct 
			jdbcUserDetailsManager.createUser(user);
			newAccount.setPassword(encodedPassword);
			db.addAcct(newAccount);

			// give system message
			model.addAttribute("sysMessage", "Account successfully created");
			model.addAttribute("alertType", "success");

			return "/login";
		}
		

		@GetMapping("/permission-denied")
		public String goPermissionDenied() {
			return "/error/permission-denied";
		}

		
		@GetMapping("/faq")
		public String goFaq() {
			return "faq";
		}

		/**
		 * checks if user is an admin or not
		 * admin - retrieves all user accounts in the db
		 * otherwise, retrieves only account of specific user
		 *
		 * @param model
		 * @return
		 */
		@GetMapping("/profile")
		public String goProfile(Model model) {
						
			boolean isAdmin = db.getAccountOf(username).getAuthority().equalsIgnoreCase("ROLE_ADMIN")? true : false;

			if(isAdmin) {
				model.addAttribute("acctList", db.getAccounts());
			} 
			else {
				model.addAttribute("acctList", db.getAccountOf(username));
			}
			
			model.addAttribute("acctID", acctID);
			model.addAttribute("username", username);
			return "secured/user/profile";
		}

		/**
		 * checks if user is an admin or not
		 * admin - retrieves all dogs in the db
		 * otherwise, retrieves only dogs of specific user
		 *  
		 * @param model
		 * @return
		 */
		@GetMapping("/pets")
		public String goPets(Model model) {
			List<Dog> dogsList;
			boolean isAdmin = db.getAccountOf(username).getAuthority().equalsIgnoreCase("ROLE_ADMIN")? true : false;

			if(isAdmin) {
				dogsList = db.getDogs();
			} 
			else {
				dogsList = db.getDogsOf(acctID);
			}
			
			model.addAttribute("acctID", acctID);
			model.addAttribute("username", username);
			model.addAttribute("dogList", dogsList);
			
			// give system message
//			model.addAttribute("sysMessage", "");
//			model.addAttribute("alertType", "");
			return "secured/user/pets";
		}

		
		@GetMapping("/appointments")
		public String goAppointments(Model model) {
			List<Appointment> apptList = db.getAppointments();
			model.addAttribute("apptList", apptList);
			return "secured/admin/appointments";
		}

		/**
		 * passes a dog object for form binding
		 * goes to add pets page
		 * @param model
		 * @return
		 */
		@GetMapping("/petsAdd")
		public String goPetsAdd(Model model) {
			
			model.addAttribute("dog", new Dog());
			
			model.addAttribute("acctID", acctID);
			model.addAttribute("username", username);
			
			// give system message
//			model.addAttribute("sysMessage", "");
//			model.addAttribute("alertType", "");
			return "secured/user/petsAdd";
		}
		 
		/**
		 * assigns acctID of user before adding dogs data to db
		 * @param dog data of new dog
		 * @return
		 */
		@PostMapping("/addAdog")
		public String addAdog(@ModelAttribute Dog dog, RedirectAttributes redirectAttrs){
			int result = 0;

			dog.setOwnerID(acctID);
			result = db.addDog(dog);

			redirectAttrs.addAttribute("acctID", acctID)
						.addFlashAttribute("sysMessage", "Add dog successful")
						.addFlashAttribute("alertType", "success");

			return "redirect:/pets";
		}

		@GetMapping("/visits")
		public String goVisits() {
			return "secured/user/visits";
		}

		@GetMapping("/aptAdd")
		public String goAptAdd() {
			return "secured/aptAdd";
		}
		
		@GetMapping("/admin")
		public String goAdmin() {
			return "secured/admin/adminIndex";
		}
}
