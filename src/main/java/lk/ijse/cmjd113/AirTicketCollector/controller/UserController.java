package lk.ijse.cmjd113.AirTicketCollector.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lk.ijse.cmjd113.AirTicketCollector.dto.UserDTO;
import lk.ijse.cmjd113.AirTicketCollector.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor

public class UserController {
        private final UserService userService;

        // Save user
        @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
            UserDTO savedUser = userService.saveUser(userDTO);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        }

        //Get Selected User
        @PostMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<UserDTO> getSelectedUser(@PathVariable ("id") String userId) {
            var userDTO = new UserDTO(
                    userId,
                    "Waruna",
                    "Munasinghe",
                    "waruna@gmail.com",
                    UserDTO.Role.USER,
                    "password123",
                    "0771234567"

            );
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }
}
