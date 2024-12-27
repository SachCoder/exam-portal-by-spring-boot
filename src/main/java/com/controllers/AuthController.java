package com.controllers;

 

import java.security.Principal;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
 import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
 import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
  import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.UserCreateDto;
import com.dto.UserLoginDto;
import com.dto.UserVerifyDto;
import com.entities.JwtResponse;
import com.entities.User;
 import com.security.CustomUserDetailsService;
import com.security.JwtTokenProvider;
import com.service.UserService;
import com.shared.BiometricMessage;

import jakarta.servlet.http.HttpServletRequest;

@RestController
 
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
    @Autowired
    private   AuthenticationManager authenticationManager;
@Autowired
    private   JwtTokenProvider jwtTokenProvider;
@Autowired
    private  UserService userService;
    
 

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
   
//	@PreAuthorize("hasRole('USER')")

//    private final SendEmailService sendEmailService;

//    @Autowired
//    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, PasswordEncoder passwordEncoder ) {
//        this.authenticationManager =authenticationManager;
//        this.passwordEncoder = passwordEncoder;
//        this.userService = userService;
//        this.jwtTokenProvider = jwtTokenProvider;
////        this.sendEmailService = sendEmailService;
//    }
    
    

//     @RequestMapping(value = "/login", method = RequestMethod.POST)
    @PostMapping("/login")
    public ResponseEntity<?> generateToken(@RequestBody UserLoginDto userLoginDto) throws Exception {
//        System.out.println("login");
    	 try {
      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword());
//            System.out.println(authenticationToken);
	        Authentication auth = authenticationManager.authenticate(authenticationToken);
//	        System.out.println(auth);
	        if(auth.isAuthenticated()) {
 
	        	 
//	 			if (userService.findByEmail(userLoginDto.getEmail()).getStatus().equals("active")) {
 	 				if(userService.findByEmail(userLoginDto.getEmail()).isBlocked()==true) {
 
	 	                return new ResponseEntity<>(BiometricMessage.USER_BLOCKED_CREATED , HttpStatus.FORBIDDEN);
	 				}
	 				 
	 			
	        SecurityContextHolder.getContext().setAuthentication(auth);
	        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
//	       String status =  userService.findByEmail(userLoginDto.getEmail()).getStatus();
//	        Collection<? extends GrantedAuthority> userRole = auth.getAuthorities();
//	       System.out.println(userRole[0]);
	        return ResponseEntity.ok(new JwtResponse(jwtToken));

//        }
//	 			else {
//	 				SecurityContextHolder.getContext().setAuthentication(auth);
//	 		        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
////	 		        System.out.println(jwtToken);
////	 		        return "Bearer " + jwtToken;
//	 		       String status =  userService.findByEmail(userLoginDto.getEmail()).getStatus();
//
//	 		       Collection<? extends GrantedAuthority> userRole = auth.getAuthorities();
//	 		        return ResponseEntity.ok(new JwtResponse(jwtToken,userRole.toString(),status));
////	 		       return new ResponseEntity<>(BiometricMessage.USER_NOT_LOGIN , HttpStatus.UNAUTHORIZED);
//	 			}
        }
        }
         
	 			catch (UsernameNotFoundException e) {
            e.printStackTrace();
            throw new Exception("Bad Credentials");
        }catch (BadCredentialsException e)
        {
            e.printStackTrace();
            throw new Exception("Bad Credentials");
        }
        return new ResponseEntity<>(BiometricMessage.USER_NOT_LOGIN , HttpStatus.UNAUTHORIZED);
    }
     
  
	
	
	@PostMapping("/register")
    public ResponseEntity<?> addUser( @RequestBody UserCreateDto user) {
 
            if(userService.findByEmail(user.getEmail()) != null) {
            	 return new ResponseEntity<>(BiometricMessage.USER_ALREADY_EXIST , HttpStatus.CREATED);
	        }
	        userService.add(user);
//                System.out.println("success");            
                return new ResponseEntity<>(BiometricMessage.USER_CREATED, HttpStatus.CREATED);
    }

 
	@PostMapping("/verify")
	public ResponseEntity<?> verify( @RequestBody UserVerifyDto userVerifyDto ) {
		try {
		Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null)
		{
			Object principal =  authentication.getPrincipal();
			if(principal instanceof UserDetails)
			{
				String email = ((UserDetails) principal).getUsername();
			 
// System.out.println(email);
// String string = null;
					
					String string = userService.verify(email, userVerifyDto.getOtp());
//					System.out.println(string);
					if (string != null) {
//						String i = null;
				        return ResponseEntity.ok(new UserVerifyDto(userVerifyDto.getOtp(), string));

//						return new ResponseEntity<>(BiometricMessage.OTP_AUTH_SUCCESS, HttpStatus.CREATED);
					}  
					 else {
							return new ResponseEntity<>(BiometricMessage.OTP_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);					 
						}
					}
			}
	}
			catch (Exception e) {
						e.printStackTrace();
					}	
	  return new ResponseEntity<>(BiometricMessage.OTP_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	@GetMapping("/resend")
	public ResponseEntity<?> resend() {

		try {
		Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
 System.out.println(authentication);
		if(authentication != null)
		{
			Object principal =  authentication.getPrincipal();
			if(principal instanceof UserDetails)
			{
				String email = ((UserDetails) principal).getUsername();
 
//					if ("resend".equals(userVerifyDto.getAction())) {
					UserVerifyDto user = userService.resendOtp(email);
//					System.out.println(user);	
					if (user != null) {
						 
						return new ResponseEntity<>(BiometricMessage.OTP_RESEND_SUCCESS, HttpStatus.CREATED);
					} 
				}
				else {
					return new ResponseEntity<>(BiometricMessage.OTP_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			
		}
	}
			catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
 
		return null;
	}
	
	 
	@PostMapping("/forgetPass")
	public ResponseEntity<?> forgetPass(@RequestBody UserCreateDto userDto,RedirectAttributes redirectAttributes,HttpServletRequest servletRequest) {
//		System.out.println(userDto.getEmail());
		User userByEmail = userService.getByUsername(userDto.getEmail());
//		System.out.println(userByEmail.getId());
//		System.out.println(userByEmail.getEmail());
		if(userByEmail!=null) {
			
			StringBuffer url = servletRequest.getRequestURL();
			String url2 = url.substring(0, url.indexOf("/forgetPass"));
			System.out.println(url2);
		com.extras.EmailSender.sendPasswordResetLink(userByEmail.getEmail(), url2+"/resetPass?id="+userByEmail.getId());
		redirectAttributes.addFlashAttribute("msg", "A password reset link has been successfully sent to your email address. Please check your inbox and spam folder, and follow the instructions to reset your password.");
		return new ResponseEntity<>(BiometricMessage.PASSWORD_SENT_SUCCESS , HttpStatus.OK);
		}
		return new ResponseEntity<>(BiometricMessage.USER_ALREADY_EXIST , HttpStatus.UNAUTHORIZED);
	}
	
	@GetMapping("/current-user")
	public User userInfo(Principal principal) {
		return ((User)     this.customUserDetailsService.loadUserByUsernameList(principal.getName())) ;
		
	}
}

	


