package com.iauto.eschool.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.iauto.eschool.config.security.AuthUser;
import com.iauto.eschool.entity.Role;
import com.iauto.eschool.entity.User;
import com.iauto.eschool.exception.ResourceNotFoundException;
import com.iauto.eschool.repository.UserRepository;
import com.iauto.eschool.service.UserService;

import net.bytebuddy.utility.RandomString;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
    private JavaMailSender mailSender;
	
	@Override
	public User getById(Long id) {
		return userRepository.findById(id)
					.orElseThrow(()-> new ResourceNotFoundException("user id", 0));
		
	}
 
	@Override
	public User getByUsername(String userName) {
		User user = userRepository.findByUsername(userName).stream().findFirst()
			.orElseThrow(()-> new ResourceNotFoundException("username " + userName, 0));
		return user;
	}

	@Override
	public Optional<AuthUser> findAuthUserByUsername(String username) {
		User user = userRepository.findByUsername(username)
			.orElseThrow(()-> new ResourceNotFoundException("user "+ username, 0));
		
		AuthUser authUser = AuthUser.builder()
				.username(user.getUsername())
				.password(user.getPassword())
				.authorities(getAuthorities(user.getRoles()))
				.accountNonExpired(user.isAccountNonExpired())
				.accountNonLocked(user.isAccountNonLocked())
				.credentialsNonExpired(user.isCredentialsNonExpired())
				.enabled(user.isEnabled())
				.build();
		return Optional.ofNullable(authUser);
		
	}
	
	private Set<SimpleGrantedAuthority> getAuthorities(Set<Role> roles){
		Set<SimpleGrantedAuthority> authorities1 = roles.stream()
			.map(role -> new SimpleGrantedAuthority("ROLE_"+ role.getName()))
			.collect(Collectors.toSet());
		
		Set<SimpleGrantedAuthority> authorities = roles.stream()
				.flatMap(role ->toStream(role))
				.collect(Collectors.toSet());
		authorities.addAll(authorities1);
		return authorities;
	}
	
	private Stream<SimpleGrantedAuthority> toStream(Role role){
		return role.getPermissions().stream()
			.map(permission -> new SimpleGrantedAuthority(permission.getName()));
	}

	@Override
	public User register(User user) {
		//Testing only
		user.setPassword( passwordEncoder.encode(user.getPassword()) );
		
		user.setEnabled(false);
		
		return userRepository.save(user);
		
	}

	private void sendVerificationEmail(User user, String siteURL)
	        throws MessagingException, UnsupportedEncodingException {
	    
		String toAddress = user.getEmail();
	    String fromAddress = "sonaseng.kh@gmail.com";
	    String senderName = "eSchool";
	    String subject = "Please verify your registration";
	    String content = "Dear [[name]],<br>"
	            + "Please click the link below to verify your registration:<br>"
	            + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
	            + "Thank you,<br>"
	            + "Your company name.";
	     
	    MimeMessage message = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	     
	    helper.setFrom(fromAddress, senderName);
	    helper.setTo(toAddress);
	    helper.setSubject(subject);
	     
	    content = content.replace("[[name]]", user.getLastName());
	    String verifyURL = siteURL + "/users/verify?code=" + user.getVerificationCode();
	     
	    content = content.replace("[[URL]]", verifyURL);
	     
	    helper.setText(content, true);
	     
	    mailSender.send(message);
	     
	}

	@Override
	public void register_email(User user, String siteURL) 
			throws UnsupportedEncodingException, MessagingException 
	{
		String encodedPassword = passwordEncoder.encode(user.getPassword());
	    user.setPassword(encodedPassword);
	     
	    String randomCode = RandomString.make(64);
	    user.setVerificationCode(randomCode);
	    user.setEnabled(false);
	     
	    userRepository.save(user);
	     
	    sendVerificationEmail(user, siteURL);
		
	}

	@Override
	public boolean verify(String verificationCode) {
		User user = userRepository.findByVerificationCode(verificationCode);
	     
	    if (user == null || user.isEnabled()) {
	        return false;
	    } else {
	        user.setVerificationCode(null);
	        user.setEnabled(true);
	        userRepository.save(user);
	         
	        return true;
	    }
	}


	
	

}
