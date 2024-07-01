package com.feastify.controller;

import com.feastify.Repository.CartRepo;
import com.feastify.Repository.UserRepo;
import com.feastify.config.JwtProvider;
import com.feastify.model.Cart;
import com.feastify.model.USER_ROLE;
import com.feastify.model.User;
import com.feastify.request.LoginRequest;
import com.feastify.response.AuthResponse;
import com.feastify.service.CustomerUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CustomerUserDetailService customerUserDetailService;

    @Autowired
    private CartRepo cartRepo;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception {

        User isEmailExist=userRepo.findByEmail(user.getEmail());
        if(isEmailExist!=null){
            throw new Exception("Email is already linked with another account");
        }

        User creatUser=new User();

        creatUser.setEmail(user.getEmail());
        creatUser.setFullName(user.getFullName());
        creatUser.setRole(user.getRole());
        creatUser.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser=userRepo.save(creatUser);
        Cart cart =new Cart();

        cart.setCustomer(savedUser);
        cartRepo.save(cart);

        Authentication authentication=new
                UsernamePasswordAuthenticationToken
                (user.getEmail(),user.getPassword());// Authentication

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt=jwtProvider.generateToken(authentication);//token generation

        AuthResponse authResponse=new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Register success");
        authResponse.setRole(savedUser.getRole());
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signIn(@RequestBody LoginRequest request){
        String userName=request.getEmail();
        String password=request.getPassword();
        User user =new User();


        Authentication authentication=authenticate(userName,password);
        Collection<? extends GrantedAuthority>authorities=authentication.getAuthorities();
        String role=authorities.isEmpty()?null:authorities.iterator().next().getAuthority();;
        String jwt=jwtProvider.generateToken(authentication);//token generation

        AuthResponse authResponse=new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Signed In successfully");
        authResponse.setRole(user.getRole());
       // authResponse.setRole(savedUser.getRole());
        return new ResponseEntity<>(authResponse, HttpStatus.OK);


    }

    private Authentication authenticate(String userName, String password) {
        UserDetails userDetails=customerUserDetailService.loadUserByUsername(userName);

        if (userDetails == null) {
            throw new BadCredentialsException("!Invalid Username..");
        }
        if (!passwordEncoder.matches(password,userDetails.getPassword())) {
            throw new BadCredentialsException("!Invalid Password");
            
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}
