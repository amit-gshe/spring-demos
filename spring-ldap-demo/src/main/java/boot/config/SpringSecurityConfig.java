package boot.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.authentication.SpringSecurityAuthenticationSource;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	Environment env;

	@Autowired
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.eraseCredentials(false);
		auth.authenticationProvider(adAuthenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/ldap").hasRole("HR").antMatchers("/roles").hasRole("Employee")
				.anyRequest().authenticated();
		http.csrf().disable();
		http.formLogin().loginPage("/login").permitAll();
		http.logout().logoutSuccessUrl("/login");
		http.exceptionHandling().accessDeniedPage("/403");
	}

	private ActiveDirectoryLdapAuthenticationProvider adAuthenticationProvider() {
		ActiveDirectoryLdapAuthenticationProvider provider = new ActiveDirectoryLdapAuthenticationProvider(
				env.getProperty("ldap.domain"), env.getProperty("ldap.url"));
		provider.setUserDetailsContextMapper(mapGrantedAuthorities());
		return provider;
	}

	private UserDetailsContextMapper mapGrantedAuthorities() {

		return new UserDetailsContextMapper() {
			@Override
			public UserDetails mapUserFromContext(DirContextOperations ctx, String username,
					Collection<? extends GrantedAuthority> authorities) {
				// Drop the groups attribute and add a default employee
				// authority
				ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
				grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_Employee"));
				// Authorize role based on the department attribute
				grantedAuthorities.addAll(getAuthoryties(ctx));
				UserDetailsContextMapper contextMapper = new LdapUserDetailsMapper();
				return contextMapper.mapUserFromContext(ctx, username, grantedAuthorities);
			}

			@Override
			public void mapUserToContext(UserDetails user, DirContextAdapter ctx) {
			}
		};
	}

	@Bean
	public LdapTemplate ldapTemplate() throws Exception {
		LdapTemplate ldapTemplate = new LdapTemplate();
		ldapTemplate.setContextSource(getContextSource());
		return ldapTemplate;
	}

	private ContextSource getContextSource() {
		LdapContextSource contextSource = new LdapContextSource();
		contextSource.setUrl(env.getProperty("ldap.url"));
		SpringSecurityAuthenticationSource authenticationSource = new SpringSecurityAuthenticationSource();
		contextSource.setAuthenticationSource(authenticationSource);
		contextSource.afterPropertiesSet();
		return contextSource;
	}

	private List<GrantedAuthority> getAuthoryties(DirContextOperations ctx) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		String[] departments = ctx.getStringAttributes("department");
		if (departments != null) {
			for (String department : departments) {
				if (department.trim().equals("HR")) {
					authorities.add(new SimpleGrantedAuthority("ROLE_HR"));
				}
				if (department.trim().equals("PM")) {
					authorities.add(new SimpleGrantedAuthority("ROLE_PM"));
				}
				if (department.trim().equals("EM")) {
					authorities.add(new SimpleGrantedAuthority("ROLE_EM"));
				}
			}
		}
		return authorities;
	}

	public static void main(String[] args) {

		String username = "TM-admin";
		String password = "ecwise@123";

		ActiveDirectoryLdapAuthenticationProvider provider = new ActiveDirectoryLdapAuthenticationProvider(
				"ecwise.local", "ldap://192.168.1.3:389");
		provider.setConvertSubErrorCodesToExceptions(true);
		provider.setUseAuthenticationRequestCredentials(true);
		provider.setAuthoritiesMapper(new GrantedAuthoritiesMapper() {

			@Override
			public Collection<? extends GrantedAuthority> mapAuthorities(
					Collection<? extends GrantedAuthority> authorities) {
				for (GrantedAuthority authority : authorities) {
					System.out.println(authority.getAuthority());
				}
				return null;
			}
		});
		// provider.authenticate(new
		// UsernamePasswordAuthenticationToken(username, password));

		//
		try {
			LdapContextSource ldapContextSource = new LdapContextSource();
			ldapContextSource.setUrl("ldap://192.168.1.3:389");
			ldapContextSource.setBase("dc=ecwise,dc=local");
			ldapContextSource.setUserDn(username + "@ecwise.local");
			ldapContextSource.setPassword(password);

			ldapContextSource.afterPropertiesSet();

			LdapTemplate ldapTemplate = new LdapTemplate(ldapContextSource);
			ldapTemplate.setIgnorePartialResultException(true);
			AndFilter filter = new AndFilter();
			filter.and(new EqualsFilter("userPrincipalName", username + "@ecwise.local"));

			ldapTemplate.authenticate(DistinguishedName.EMPTY_PATH, filter.toString(), password);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
