package boot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import boot.dto.UserDto;
import boot.service.UserService;

@RestController
@RequestMapping("/user/")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public Map<String, Object> signUp(UserDto userDto) {
		Map<String, Object> result = new HashMap<String, Object>();
		userService.signUp(userDto);
		return result;
	}

	@RequestMapping("/{id}")
	public UserDto loadUser(@PathVariable Long id) {
		return userService.loadUser(id);
	}
}
