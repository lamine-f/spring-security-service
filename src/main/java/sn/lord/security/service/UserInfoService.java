package sn.lord.security.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails; 
import org.springframework.security.core.userdetails.UserDetailsService; 
import org.springframework.security.core.userdetails.UsernameNotFoundException; 
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.stereotype.Service;
import sn.lord.security.auth.UserInfoDetails;
import sn.lord.security.entity.UserInfo;
import sn.lord.security.repository.UserInfoRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserInfoService implements UserDetailsService { 

	@Autowired
	private UserInfoRepository repository;

	@Autowired
	private PasswordEncoder encoder; 

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 

		Optional<UserInfo> userDetail = repository.findByName(username);

		// Converting userDetail to UserDetails 
		return userDetail.map(UserInfoDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("User not found " + username)); 
	} 

	public UserInfo addUser(UserInfo userInfo) {
		if (userInfo.getId() == null) {
			userInfo.setId(UUID.randomUUID().toString());
		}
		userInfo.setPassword(encoder.encode(userInfo.getPassword()));
		return repository.save(userInfo);
	}
} 
