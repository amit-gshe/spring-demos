package boot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	public void signin(String username, String password) {
		if ("admin".equals(username) && "password".equals(password)) {
			List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();
			AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username,
					password, AUTHORITIES);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} else {
			throw new BadCredentialsException("用户名或密码错误");
		}
	}
	
	@Secured("ADMIN")
	public void protectedMethod(){
		
	}
}
