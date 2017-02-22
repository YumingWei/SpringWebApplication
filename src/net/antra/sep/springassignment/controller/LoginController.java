
package net.antra.sep.springassignment.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.antra.sep.springassignment.service.UserService;

@Controller
@RequestMapping({""})
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping()
	public String showIndex(ModelMap model) {
		return "main";
	}
	
	@RequestMapping("/login")
	public String showLogin(ModelMap model) {
		return "login";
	}

	@RequestMapping(value = "/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("In the logout processing");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	@RequestMapping(value = "/deny", method = RequestMethod.GET)
	public String accessDenyPage(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("In the deny processing");
		return "deny";
	}
}
