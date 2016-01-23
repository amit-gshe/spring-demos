package boot.web;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	@Autowired
	LdapTemplate ldapTemplate;

	@RequestMapping("/ldap")
	public List<String> getLdapInfo() {
		LdapQuery query = query().base("OU=Accounts,OU=Chengdu,OU=EC Wise Users,DC=ecwise,DC=local");
		return ldapTemplate.list(query.base());
	}

	@RequestMapping("/roles")
	public List<GrantedAuthority> getRoles() {
		return new ArrayList<GrantedAuthority>(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
	}
}
