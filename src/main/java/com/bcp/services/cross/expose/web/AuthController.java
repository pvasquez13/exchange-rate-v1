package com.bcp.services.cross.expose.web;

import com.bcp.services.cross.exchangerate.business.security.AuthorizationService;
import com.bcp.services.cross.exchangerate.model.api.security.LoginRequest;
import com.bcp.services.cross.exchangerate.model.api.security.UserRequest;
import com.bcp.services.cross.exchangerate.security.JwtAuthResponseDTO;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(tags = "Authorization")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthorizationService authorizationService;

    /**
     * Login endpoint
     *
     * @param loginRequest parameter with the data for login
     * @return {@link JwtAuthResponseDTO} Token
     */
    @ApiOperation(
            value = "login",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "POST"
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Login successfully.",
                    response = JwtAuthResponseDTO.class
            ),
            @ApiResponse(
                    code = 400,
                    message = "the data sent is incorrect."
            ),
            @ApiResponse(
                    code = 500,
                    message = "Internal error."
            )
    })
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/login", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Single<JwtAuthResponseDTO> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return authorizationService.login(loginRequest);
    }

    /**
     * Endpoint that registers a new user
     *
     * @param userRequest parameter with the data of the user
     */
    @ApiOperation(
            value = "Allows to register a new user",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "POST"
    )
    @ApiResponses({
            @ApiResponse(code = 204, message = "Successful registration."),
            @ApiResponse(code = 400, message = "The data sent is incorrect."),
            @ApiResponse(code = 500, message = "Error trying to register new user")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/register")
    public Completable createUser(@Valid @RequestBody UserRequest userRequest) {
        return authorizationService.register(userRequest);
    }
}
