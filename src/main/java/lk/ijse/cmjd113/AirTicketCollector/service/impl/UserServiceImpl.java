package lk.ijse.cmjd113.AirTicketCollector.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import lk.ijse.cmjd113.AirTicketCollector.dao.UserDao;
import lk.ijse.cmjd113.AirTicketCollector.entities.UserEntity;
import lk.ijse.cmjd113.AirTicketCollector.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import lk.ijse.cmjd113.AirTicketCollector.service.UserService;
import lk.ijse.cmjd113.AirTicketCollector.dto.UserDTO;
import lk.ijse.cmjd113.AirTicketCollector.util.IDGenerate;
import jakarta.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final Mapper mapper;

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        userDTO.setUserId(IDGenerate.userId());
        UserEntity userEntity = mapper.toUserEntity(userDTO);
        userEntity.setFullName(userDTO.getFirstName() + " " + userDTO.getLastName());
        UserEntity savedUser = userDao.save(userEntity);
        return convertToDTO(savedUser);
    }

    @Override
    public UserDTO getUser(String userId) {
        UserEntity userEntity = userDao.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDTO(userEntity);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userDao.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    
    @Override
    public void deleteUser(String userId) {
        UserEntity userEntity = userDao.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        userDao.delete(userEntity);
    }

    @Override
    public void updateUser(String userId, UserDTO updatedUser) {
        UserEntity existingUser = userDao.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setFullName(updatedUser.getFirstName() + " " + updatedUser.getLastName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setRole(updatedUser.getRole());
        existingUser.setPhone(updatedUser.getPhone());
        userDao.save(existingUser);
    }

    private UserDTO convertToDTO(UserEntity userEntity) {
        UserDTO userDTO = mapper.toUserDTO(userEntity);
        if (userEntity.getFullName() != null) {
            int lastSpace = userEntity.getFullName().lastIndexOf(' ');
            if (lastSpace != -1) {
                userDTO.setFirstName(userEntity.getFullName().substring(0, lastSpace));
                userDTO.setLastName(userEntity.getFullName().substring(lastSpace + 1));
            } else {
                userDTO.setFirstName(userEntity.getFullName());
                userDTO.setLastName("");
            }
        }
        return userDTO;
    }
}
