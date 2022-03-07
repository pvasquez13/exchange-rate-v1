package com.bcp.services.cross.exchangerate.security;

import com.bcp.services.cross.exchangerate.model.entity.security.Rol;
import com.bcp.services.cross.exchangerate.model.entity.security.User;
import com.bcp.services.cross.exchangerate.repository.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
    User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
            .orElseThrow(() -> new UsernameNotFoundException("User not found By username or email : "
                    + usernameOrEmail));

    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
            mapRoles(user.getRoles()));
  }

  private Collection<? extends GrantedAuthority> mapRoles(Set<Rol> roles) {
    return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getName())).collect(Collectors.toList());
  }
}
