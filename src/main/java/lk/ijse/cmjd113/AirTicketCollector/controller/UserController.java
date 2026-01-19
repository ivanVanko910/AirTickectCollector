package lk.ijse.cmjd113.AirTicketCollector.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lk.ijse.cmjd113.AirTicketCollector.dto.UserDTO;
import lk.ijse.cmjd113.AirTicketCollector.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor

public class UserController {
        private final UserService userService;
        // Save user
        @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
            return new ResponseEntity<>(userService.saveUser(userDTO), HttpStatus.CREATED);
        }
        //Get Selected User
        @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<UserDTO> getSelectedUser(@PathVariable ("id") String userId) {
            var userDTO = new UserDTO(
                    userId,
                    "Waruna",
                    "Munasinghe",
                    "waruna@gmail.com",
                    UserDTO.Role.ADMIN,
                    "password123",
                    "0771234567"

            );
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }
        //Get All Users
        @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<List<UserDTO>> getAllUsers() {
            List<UserDTO> allUsers = List.of(
                    new UserDTO("USR-001", "Waruna", "Munasinghe", "waruna@gmail.com", UserDTO.Role.USER, "password123", "0771234567"),
                    new UserDTO("USR-002", "Saman", "Perera", "saman@gmail.com", UserDTO.Role.USER, "password456", "0779876543"),
                    new UserDTO("USR-003", "Nimal", "Silva", "nimal@gmail.com", UserDTO.Role.USER, "password789", "0771112222"),
                    new UserDTO("USR-004", "Kamal", "Fernando", "kamal@gmail.com", UserDTO.Role.USER, "password012", "0773334444"),
                    new UserDTO("USR-005", "Ajith", "Kumar", "ajith@gmail.com", UserDTO.Role.USER, "password345", "0775556666")
            );
            return new ResponseEntity<>(allUsers, HttpStatus.OK);
        }  
        //Delete a User
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteUser(@PathVariable("id") String userId) {
            System.out.println("Deleted Id is: " + userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        // Update a User
        @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Void> updateUser(
                @PathVariable("id") String userId,
                @RequestBody UserDTO updatedUser) {
            updatedUser.setUserId(userId);
            System.out.println("Updated Id is: " + userId);
            System.out.println("Updated User: " + updatedUser);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }