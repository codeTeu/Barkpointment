package marjorieTeu.barkpointment.controllers;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import marjorieTeu.barkpointment.beans.Dog;
import marjorieTeu.barkpointment.beans.Account;
import marjorieTeu.barkpointment.beans.Appointment;
import marjorieTeu.barkpointment.database.DatabaseAccess;


@Controller
public class HomeController {
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


		@GetMapping("/")
		public String goHome() {
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
		
		@GetMapping("/securedIndex")
		public String goSecuredIndex(){
			return "secured/index";
		}

		@GetMapping("/register")
		public String goRegister(Model model) {
			model.addAttribute("account", new Account());
			return "register";
		}

		
		@PostMapping("/createUser")
		public String addUser(
				@ModelAttribute Account newAccount,
				RedirectAttributes redirectAttributes,
				Model model) {

			//catch if username exist
			boolean userExist =   jdbcUserDetailsManager.userExists(newAccount.getUsername());
			
			if(userExist) {
				model.addAttribute("sysMessage", "Username already exist");
				model.addAttribute("alertType", "danger");
				model.addAttribute("account", newAccount);
				return "/register";
			}
			
			//add new account

			String encodedPassword = passwordEncoder.encode(newAccount.getPassword());

			List<GrantedAuthority> authorityList = new ArrayList<>();
			authorityList.add(new SimpleGrantedAuthority(newAccount.getAuthority()));

			User user = new User(newAccount.getUsername(), encodedPassword, authorityList);

			// create user and authority
			jdbcUserDetailsManager.createUser(user);

			// create account
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

		@GetMapping("/profile")
		public String goProfile() {
			return "secured/user/profile";
		}

		@GetMapping("/pets")
		public String goPets(Model model) {
			List<Dog> dogs = db.getDogs();
			model.addAttribute("dogList", dogs);
			return "secured/user/pets";
		}

		@GetMapping("/accounts")
		public String goAccounts(Model model) {
			List<Account> acctList = db.getAccounts();
			model.addAttribute("acctList", acctList);
			return "secured/admin/accounts";
		}
		
		@GetMapping("/appointments")
		public String goAppointments(Model model) {
			List<Appointment> apptList = db.getAppointments();
			model.addAttribute("apptList", apptList);
			return "secured/admin/appointments";
		}

		@GetMapping("/petsAdd")
		public String goPetsAdd(Model model) {
			model.addAttribute("dog", new Dog());
			return "secured/user/petsAdd";
		}

		@PostMapping("/addAdog")
		public String addAdog(@ModelAttribute Dog dog) {
			int result = db.addDog(dog);

			System.out.println(result);
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
			return "secured/admin/admin";
		}
}
