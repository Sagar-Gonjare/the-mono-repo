package org.dnyanyog.service;

import java.util.List;
import java.util.Optional;

import org.dnyanyog.dto.request.LoginRequest;
import org.dnyanyog.dto.response.LoginResponse;
import org.dnyanyog.encryption.EncryptionService;
import org.dnyanyog.entity.Users;
import org.dnyanyog.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class LoginServiceImpl extends LoginService {
  @Autowired LoginResponse response;
  @Autowired UserRepo repo;
  @Autowired EncryptionService encryptionService;

  public LoginResponse userLogin(LoginRequest request) throws Exception {
    response = new LoginResponse();
    Optional<Users> userData =  repo.findByUserName(request.getUserName());
    if (userData != null) {
      Users data = userData.get();
      String encryptedPassword = data.getPassword();
      String requestPass = encryptionService.Encrypt(request.getPassword());

      if (requestPass.equalsIgnoreCase(encryptedPassword)) {
    	   
        response.setResponseCode(HttpStatus.OK.value());
        response.setResponseMsg("Login Successfull");
      } else {
    	  
        response.setResponseCode(HttpStatus.UNAUTHORIZED.value());
        response.setResponseMsg("Username & Password Do Not Match");
      }
    } else {
      response.setResponseCode(HttpStatus.BAD_REQUEST.value());
      response.setResponseMsg("Request Username is Not present in the database");
    }

    return response;
  }
}
