package sn.lord.ensae.hint.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.lord.ensae.hint.security.entity.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
     Optional<UserInfo> findByName(String username); 
}
