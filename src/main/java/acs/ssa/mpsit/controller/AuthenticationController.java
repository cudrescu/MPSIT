package acs.ssa.mpsit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import acs.ssa.mpsit.dto.User;
import acs.ssa.mpsit.dto.UserCredentials;
import acs.ssa.mpsit.service.AuthenticationService;

@Controller
@RequestMapping(value = "/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public User login(@RequestBody UserCredentials userCredentials) {
		return authenticationService.login(userCredentials);
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public User register(@RequestBody User user) {
		return authenticationService.register(user);
	}

}
