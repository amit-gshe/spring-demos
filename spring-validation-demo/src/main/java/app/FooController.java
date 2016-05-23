package app;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.validator.constraints.Email;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class FooController {

	@RequestMapping("/email")
	public Map<String, Object> validate(@Email(message="请输入合法的email地址") @RequestParam String email){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("email", email);
		return result;
	}
	
}
