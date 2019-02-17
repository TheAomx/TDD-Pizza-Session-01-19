package de.ov.software.kata.tdd.y2019.v01.usecases.login;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

import de.ov.software.kata.tdd.y2019.v01.entities.LoginSession;
import de.ov.software.kata.tdd.y2019.v01.entities.User;
import de.ov.software.kata.tdd.y2019.v01.gateways.LoginSessionGateway;
import de.ov.software.kata.tdd.y2019.v01.gateways.UserGateway;
import de.ov.software.kata.tdd.y2019.v01.utils.Context;

public class LoginUseCase {
	private final static int TOKEN_LENGTH = 128;

	private LoginSession createLoginSession(LoginRequestModel request, User user) {
		LoginSession session = new LoginSession();
		session.ipAddress = request.ipAddress;
		session.mailAddress = user.mailAddress;
		session.token = createRandomToken(TOKEN_LENGTH);
		session.validFrom = LocalDateTime.now();
		session.validUntil = LocalDateTime.now().plusDays(14);
		return session;
	}

	private String createRandomToken(int length) {
		final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_";
		final SecureRandom RANDOM = new SecureRandom();

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; ++i) {
			sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		return sb.toString();
	}

	public void handleLogin(LoginRequestModel request, LoginOutputBoundary presenter) {
		LoginResponseModel response = new LoginResponseModel();
		response.mail = request.mail;

		UserGateway userGateway = Context.userGateway;
		Optional<User> userOptional = userGateway.findUserByMail(request.mail);

		if (!userOptional.isPresent()) {
			response.loginState = LoginState.INVALID_MAIL;
		} else {
			User user = userOptional.get();
			if (user.passwordHash.equals(createPasswordHash(request.password))) {
				LoginSessionGateway sessionGateway = Context.sessionGateway;
				response.loginState = LoginState.VALID_LOGIN;
				response.username = user.username;
				LoginSession session = createLoginSession(request, user);
				response.session = sessionGateway.save(session);
			} else {
				response.loginState = LoginState.INVALID_PASSWORD;
			}
		}

		presenter.present(response);
	}

	public static String createPasswordHash(String string) {
		// TODO: Improve hashing of passwords with salt and more iterations...
		return string;
	}
}
