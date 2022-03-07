package com.bcp.services.cross.exchangerate.business.security.impl;

import com.bcp.services.cross.exchangerate.business.security.AuthorizationService;
import com.bcp.services.cross.exchangerate.model.api.security.LoginRequest;
import com.bcp.services.cross.exchangerate.model.api.security.UserRequest;
import com.bcp.services.cross.exchangerate.model.entity.security.Rol;
import com.bcp.services.cross.exchangerate.model.entity.security.User;
import com.bcp.services.cross.exchangerate.repository.security.RolRepository;
import com.bcp.services.cross.exchangerate.repository.security.UserRepository;
import com.bcp.services.cross.exchangerate.security.JwtAuthResponseDTO;
import com.bcp.services.cross.exchangerate.security.JwtTokenProvider;
import com.bcp.services.cross.exchangerate.utils.constant.Constants;
import com.bcp.services.cross.exchangerate.utils.error.entity.EntityBadRequestException;
import io.reactivex.Completable;
import io.reactivex.Single;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private RolRepository rolRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public Single<JwtAuthResponseDTO> login(LoginRequest loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String token = jwtTokenProvider.generateToken(authentication);
    return Single.just(new JwtAuthResponseDTO(token));
  }

  @Override
  public Completable register(UserRequest userRequest) {

    if (userRepository.existsByUsername(userRequest.getUsername()))
      return Completable.error(new EntityBadRequestException("username already exists"));

    if (userRepository.existsByEmail(userRequest.getEmail()))
      return Completable.error(new EntityBadRequestException("Email already exists"));

    User user = new User();
    modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
    modelMapper.map(userRequest, user);
    user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
    String role = Constants.PREFIX_ROLE.concat(userRequest.getRole());
    Rol roles = rolRepository.findByName(role).orElseThrow(() -> new UsernameNotFoundException("Rol not found"));
    user.setRoles(Collections.singleton(roles));
    userRepository.save(user);

    return Completable.complete();
  }
}
