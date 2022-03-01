package net.longvan.training.spring.DemoController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.longvan.training.spring.jwt.JwtUtil;
import net.longvan.training.spring.model.LoginRequest;

@RestController
public class LoginController {
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequest user) {
		try {
			LOGGER.info("Đây là chức năng đăng nhập");
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
			return new ResponseEntity<>(jwtUtil.generateToken(user.getEmail()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Email hoặc mật khẩu không hợp lệ", HttpStatus.NOT_FOUND);
		}
	}
}
