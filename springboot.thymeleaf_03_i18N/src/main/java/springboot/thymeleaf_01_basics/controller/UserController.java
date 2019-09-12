package springboot.thymeleaf_01_basics.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import springboot.thymeleaf_01_basics.entities.User;
import springboot.thymeleaf_01_basics.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("createuser")
	public ModelAndView createUserView() {
		ModelAndView mav = new ModelAndView("createuser");
		mav.addObject("user", new User());
		mav.addObject("allProfiles", getProfiles());
		return mav;
	}
	
	@PostMapping("createuser")
	public ModelAndView insertUserView(@Valid User myUser, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors()) {
			logger.info("Validation Error in inserting the values.");
			mav.setViewName("createuser");
			mav.addObject("user", myUser);
			return mav;
//			ModelAndView mav1 = new ModelAndView("redirect:/user/createuser");
//			return mav1;
		}
		userService.addUser(myUser);
		mav.addObject("allUsers", userService.getAllUsers());
		mav.setViewName("userInfo");
		logger.info("Form Submitted Successfully.");
		return mav;
	}
	
	private List<String> getProfiles() {
		List<String> myList = new ArrayList<>();
		myList.add("CEO");
		myList.add("Manager");
		myList.add("Director");
		myList.add("Employee");
		myList.add("Others");
		return myList;
	}
	
}
