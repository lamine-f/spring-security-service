package sn.lord.ensae.hint.security.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sn.lord.ensae.hint.security.auth.UserInfoDetails;
import sn.lord.ensae.hint.security.entity.UserInfo;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserInfoDetailsDto implements Serializable {
    private String username;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean enabled;
    private Collection<? extends GrantedAuthority> authorities;



    public static UserInfoDetailsDto ToDto (UserDetails userInfoDetails) {
        return new UserInfoDetailsDto(
                userInfoDetails.getUsername(),
                userInfoDetails.isAccountNonExpired(),
                userInfoDetails.isAccountNonLocked(),
                userInfoDetails.isCredentialsNonExpired(),
                userInfoDetails.isEnabled(),
                userInfoDetails.getAuthorities()
        );
    }
}
