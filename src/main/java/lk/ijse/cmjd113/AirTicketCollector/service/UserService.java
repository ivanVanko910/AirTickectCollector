package lk.ijse.cmjd113.AirTicketCollector.service;

import java.util.List;

import lk.ijse.cmjd113.AirTicketCollector.dto.UserDTO;

public interface UserService {
    UserDTO saveUser(UserDTO user);
    UserDTO getUser(String userId);
    List<UserDTO> getAllUsers();
    void deleteUser(String userId);
    void updateUser(String userId, UserDTO updatedUser);
}
