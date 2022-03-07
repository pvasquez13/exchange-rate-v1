package com.bcp.services.cross.exchangerate.business.security;

import com.bcp.services.cross.exchangerate.model.api.security.LoginRequest;
import com.bcp.services.cross.exchangerate.model.api.security.UserRequest;
import com.bcp.services.cross.exchangerate.security.JwtAuthResponseDTO;
import io.reactivex.Completable;
import io.reactivex.Single;

public interface AuthorizationService {

  Single<JwtAuthResponseDTO> login(LoginRequest loginRequest);

  Completable register(UserRequest userRequest);
}
