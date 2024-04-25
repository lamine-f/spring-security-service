package sn.lord.security.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserInfo {
    @Id
    private String id; 
    private String name; 
    private String email; 
    private String password; 
    private String roles; 
}
