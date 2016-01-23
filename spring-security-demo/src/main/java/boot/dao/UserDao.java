package boot.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Component;

import boot.dto.UserDto;

@Component
public class UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public String loadPassword(String username) {
		try {
			return jdbcTemplate.queryForObject("select password from tb_user where username=?", String.class, username);
		} catch (EmptyResultDataAccessException e) {
			throw new UsernameNotFoundException("User not found!");
		}
	}

	public List<SimpleGrantedAuthority> loadAuths(String username) {
		List<Map<String, Object>> auths = jdbcTemplate
				.queryForList("select role from tb_grant LEFT JOIN tb_user on tb_grant.user_id=tb_user.id "
						+ "LEFT JOIN tb_role on tb_grant.role_id=tb_role.id where username=?", username);
		List<SimpleGrantedAuthority> authList = new ArrayList<>();
		for (Map<String, Object> item : auths) {
			authList.add(new SimpleGrantedAuthority((String) item.get("role")));
		}
		return authList;
	}

	public void save(UserDto userDto) {

		String sql = "select count(1) from tb_user where username=?";
		if (jdbcTemplate.queryForObject(sql, Long.class, userDto.getUsername()) > 0) {
			return;
		}

		StandardPasswordEncoder passwordEncoder = new StandardPasswordEncoder();
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

		jdbcTemplate.update("insert into tb_user(username,password) values(?,?)", userDto.getUsername(),
				userDto.getPassword());

	}

	public Map<String, Object> getUser(Long id) {
		String sql = "select username,password from tb_user where id=?";
		return jdbcTemplate.queryForMap(sql, id);
	}

}
