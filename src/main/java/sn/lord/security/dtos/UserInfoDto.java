package sn.lord.security.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sn.lord.security.entity.UserInfo;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserInfoDto implements Serializable {
    private String id;
    private String name; 
    private String email; 
    private String roles;


    public static UserInfoDto ToDto (UserInfo userInfo) {
        return new UserInfoDto(
                userInfo.getId(), userInfo.getName(), userInfo.getEmail(), userInfo.getRoles()
        );
    }
}
