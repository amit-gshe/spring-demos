package boot.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import boot.dao.UserDao;
import boot.dto.UserDto;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	public void signUp(UserDto userDto) {
		userDao.save(userDto);
	}

	public UserDto loadUser(Long id) {
		Map<String, Object> user = userDao.getUser(id);
		return new UserDto((String) user.get("username"), (String) user.get("password"));
	}
}
