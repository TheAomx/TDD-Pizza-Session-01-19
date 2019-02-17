package de.ov.software.kata.tdd.y2019.v01.usecases.login.test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import de.ov.software.kata.tdd.y2019.v01.entities.User;
import de.ov.software.kata.tdd.y2019.v01.gateways.LoginSessionGateway;
import de.ov.software.kata.tdd.y2019.v01.gateways.UserGateway;
import de.ov.software.kata.tdd.y2019.v01.usecases.login.LoginOutputBoundary;
import de.ov.software.kata.tdd.y2019.v01.usecases.login.LoginRequestModel;
import de.ov.software.kata.tdd.y2019.v01.usecases.login.LoginResponseModel;
import de.ov.software.kata.tdd.y2019.v01.usecases.login.LoginState;
import de.ov.software.kata.tdd.y2019.v01.usecases.login.LoginUseCase;
import de.ov.software.kata.tdd.y2019.v01.utils.Context;
import de.ov.software.kata.tdd.y2019.v01.utils.TestSetup;

public class LoginUseCaseTest {
	private LoginUseCase usecase;
	private LoginOutputBoundarySpy presenter;
	private UserGateway userGateway;
	private LoginSessionGateway sessionGateway;

	@Before
	public void setup() {
		TestSetup.setupContext();
		this.usecase = new LoginUseCase();
		this.presenter = new LoginOutputBoundarySpy();
		this.userGateway = Context.userGateway;
		this.sessionGateway = Context.sessionGateway;
	}

	private LoginRequestModel createInvalidRequest() {
		LoginRequestModel request = new LoginRequestModel();
		request.mail = "hello@world.com";
		request.password = "Password";
		request.ipAddress = "127.0.0.1";
		return request;
	}

	private LoginRequestModel createValidLoginRequest() {
		User user = new User();
		user.username = "User";
		user.passwordHash = LoginUseCase.createPasswordHash("password");
		user.mailAddress = "user@mail.com";
		user.registeredAt = LocalDate.now();
		user.lastSeen = LocalDateTime.now();
		this.userGateway.save(user);

		LoginRequestModel loginRequest = new LoginRequestModel();
		loginRequest.mail = user.mailAddress;
		loginRequest.password = "password";
		loginRequest.ipAddress = "127.0.0.1";
		return loginRequest;
	}

	@Test
	public void testUsecaseWiring() throws Exception {
		LoginRequestModel request = createInvalidRequest();
		usecase.handleLogin(request, presenter);
		assertNotNull(presenter.response);
	}

	@Test
	public void testLoginWithUnknownMailFails() throws Exception {
		LoginRequestModel loginRequest = new LoginRequestModel();
		loginRequest.mail = "hello@world.com";
		loginRequest.password = "Password";

		usecase.handleLogin(loginRequest, presenter);

		LoginResponseModel response = presenter.response;
		assertEquals(LoginState.INVALID_MAIL, response.loginState);
		assertEquals(loginRequest.mail, response.mail);
		assertNull(response.session);
	}

	@Test
	public void testLoginWithValidMailAndPasswordIsAccepted() throws Exception {
		LoginRequestModel request = this.createValidLoginRequest();

		usecase.handleLogin(request, presenter);

		LoginResponseModel response = this.presenter.response;
		assertEquals(LoginState.VALID_LOGIN, response.loginState);
		assertEquals(request.mail, response.mail);
		assertNotNull(response.session);
		assertTrue(sessionGateway.exists(request.mail, response.session.token, request.ipAddress));
	}

	@Test
	public void testLoginWithValidMailAndInvalidPasswordIsDenied() throws Exception {
		LoginRequestModel request = this.createValidLoginRequest();
		request.password = "invalid-password";

		usecase.handleLogin(request, presenter);
		LoginResponseModel response = this.presenter.response;
		assertEquals(LoginState.INVALID_PASSWORD, response.loginState);
		assertEquals(request.mail, response.mail);
		assertNull(response.session);
	}
}
