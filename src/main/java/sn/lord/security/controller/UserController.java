package sn.lord.ensae.hint.security.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; 
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import sn.lord.ensae.hint.security.auth.AuthRequest;
import sn.lord.ensae.hint.security.dtos.UserInfoDetailsDto;
import sn.lord.ensae.hint.security.dtos.UserInfoDto;
import sn.lord.ensae.hint.security.entity.UserInfo;
import sn.lord.ensae.hint.security.jwt.JwtService;
import sn.lord.ensae.hint.security.service.UserInfoService;

import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth") 
public class UserController { 

    @Autowired
    private UserInfoService service;

    @Autowired
    private JwtService jwtService;
  
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("")
    public ResponseEntity<Map<String, Object>> addNewUser (
        @RequestBody UserInfo userInfo
    ){
        return ResponseEntity.status(200).body(
                Map.of("message", "User Added Successfully",
                        "data", UserInfoDto.ToDto(service.addUser(userInfo))
                )
        );
    }



    @GetMapping("/validateToken/{username}")
    public ResponseEntity<Map<String, Object>> validateToken(
            @PathVariable(required = false) String username,
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader
    ) {

        if (authorizationHeader == null || username == null) {
            return ResponseEntity.status(401).body(Map.of(
                    "message", "unauthorized"
            ));
        }

        String token = authorizationHeader.split(" ")[1];
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        System.out.println(userDetails.getAuthorities());
        Boolean isValid = jwtService.validateToken(token, userDetails);

        if (isValid) {
            return ResponseEntity.status(200).body(Map.of(
                    "data", UserInfoDetailsDto.ToDto(userDetails)
            ));
        }else {
            return ResponseEntity.status(401).body(Map.of(
                    "message", "unauthorized"
            ));
        }

    }

    @PostMapping("/generateToken")
    public ResponseEntity<Map<String, String>> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())); 
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(authRequest.getUsername());
            return ResponseEntity.status(200).body(Map.of(
                    "data", token
            ));
        } else {
            return ResponseEntity.status(404).body(Map.of(
                    "message", "invalid user request !"
            ));
//            throw new UsernameNotFoundException("invalid user request !");
        }
    } 
  
} 
