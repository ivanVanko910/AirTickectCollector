package lk.ijse.cmjd113.AirTicketCollector.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements Serializable{
    public enum Role {
        USER, ADMIN
    }

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private String password;
    private String phone;
}
