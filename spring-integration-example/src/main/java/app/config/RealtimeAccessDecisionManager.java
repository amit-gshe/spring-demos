package app.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.expression.WebExpressionVoter;

public class RealtimeAccessDecisionManager extends AffirmativeBased {

  /**
   * A in memory set to store the users whose authority to update
   */
  private Set<Object> usersToUpdate = Collections.newSetFromMap(new ConcurrentHashMap<>());

  public RealtimeAccessDecisionManager(List<AccessDecisionVoter<? extends Object>> decisionVoters) {
    super(decisionVoters);
  }
  
  public RealtimeAccessDecisionManager() {
    this(Arrays.asList(new AuthenticatedVoter(), new WebExpressionVoter()));
  }

  @Override
  public void decide(Authentication authentication, Object object,
      Collection<ConfigAttribute> configAttributes) throws AccessDeniedException {
    if (usersToUpdate.contains(authentication.getPrincipal())) {
      grantAdminAuthority(authentication.getPrincipal());
      usersToUpdate.remove(authentication.getPrincipal());
    }
    super.decide(authentication, object, configAttributes);
  }

  public void grantAdminAuthority(Object principal) {
    // we can update the Authentication here just like from the authentication source
    GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
    Authentication authentication =
        new UsernamePasswordAuthenticationToken(principal, null, Arrays.asList(authority));
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }

}
