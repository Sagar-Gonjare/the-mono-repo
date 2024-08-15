package org.dnyanyog.controller;

import org.dnyanyog.dto.request.LoginRequest;
import org.dnyanyog.dto.response.LoginResponse;
import org.dnyanyog.service.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	@Autowired LoginServiceImpl loginService;
	
	@PostMapping(
			path="api/v1/public/directory/userLogin",
			consumes = {"application/json", "application/xml"},
		    produces = {"application/json", "application/xml"})
	public LoginResponse userLogin(@RequestBody LoginRequest request) throws Exception {
		return loginService.userLogin(request);
	}
}

