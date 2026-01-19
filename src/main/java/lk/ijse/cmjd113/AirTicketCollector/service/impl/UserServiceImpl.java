package lk.ijse.cmjd113.AirTicketCollector.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import lk.ijse.cmjd113.AirTicketCollector.service.UserService;
import lk.ijse.cmjd113.AirTicketCollector.dto.UserDTO;
import lk.ijse.cmjd113.AirTicketCollector.util.IDGenerate;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserDTO saveUser(UserDTO user) {
        user.setUserId(IDGenerate.userId());
        return user;
    }

    @Override
    public UserDTO getSelectedUser(String userId) {
        return null;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return List.of();
    }
    
    @Override
    public void deleteUser(String userId) { }

    @Override
    public void updateUser(String userId, UserDTO updatedUser) { }
}
