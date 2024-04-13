//package com.Busybox.rewards.application.controller;
//
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.Busybox.rewards.application.dao.permissionRepository;
//import com.Busybox.rewards.application.model.UserInAdminsModel;
//import com.Busybox.rewards.application.security.UserService;
//
//@RestController
//@RequestMapping("/admin/permission")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//    
//    @Autowired permissionRepository permissionRepository;
//
//    @GetMapping("/all")
//    public ResponseEntity<?> getAllUsers() {  
//        return ResponseHandler.generateResponse(userService.getAllUsers(), HttpStatus.OK, "SUCCESS");
//    }
//
//    @GetMapping("/{userId}")
//    public UserInAdminsModel getUserById(@PathVariable long userId) {
//        return userService.getUserById(userId);
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> authenticateUser( @RequestBody Map<String, String> input) {
//    	String Email = input.get("userEmail");
//    	String Password = input.get("password");
//        return userService.authenticateUser(Email, Password);
//        
//       // return ResponseHandler.generateResponseNull(input, HttpStatus.OK);
//    }
//    }
