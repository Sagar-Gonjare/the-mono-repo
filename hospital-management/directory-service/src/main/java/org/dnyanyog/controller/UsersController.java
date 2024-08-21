package org.dnyanyog.controller;

import java.util.List;
import org.dnyanyog.dto.request.UserRequest;
import org.dnyanyog.dto.response.UserResponse;
import org.dnyanyog.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {
  @Autowired UserServiceImpl userServiceImpl;

  @PostMapping(
      path = "api/directory/v1/addUser",
      consumes = {"application/json", "application/xml"},
      produces = {"application/json", "application/xml"})
  public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest request) throws Exception {

    ResponseEntity<UserResponse> response = userServiceImpl.addUser(request);
    return response;
  }

  @PostMapping(
      path = "api/directory/v1/updateUser",
      consumes = {"application/json", "application/xml"},
      produces = {"application/json", "application/xml"})
  public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest request)
      throws Exception {
    return userServiceImpl.updateUser(request);
  }

  @GetMapping(path = "api/directory/v1/getAllUsers")
  public ResponseEntity<List<UserResponse>> getAllUsers() {
    return userServiceImpl.getAllPatients();
  }

  @GetMapping(path = "api/directory/v1/getUserByUserId/{userId}")
  public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId) {
    return userServiceImpl.getUserByUSerID(userId);
  }

  @DeleteMapping(path = "api/directory/v1/delteUser/{userId}")
  public ResponseEntity<UserResponse> deleteUser(@PathVariable Long userId) {
    UserResponse response = userServiceImpl.deleteUser(userId);
    return ResponseEntity.status(response.getResponseCode()).body(response);
  }
}
