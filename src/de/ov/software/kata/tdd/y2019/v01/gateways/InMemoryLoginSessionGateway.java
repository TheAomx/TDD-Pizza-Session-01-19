package de.ov.software.kata.tdd.y2019.v01.gateways;

import java.util.HashMap;
import java.util.Map;

import de.ov.software.kata.tdd.y2019.v01.entities.LoginSession;

public class InMemoryLoginSessionGateway implements LoginSessionGateway {

	Map<String, LoginSession> sessions = new HashMap<>();

	@Override
	public LoginSession save(LoginSession session) {
		sessions.put(session.token, session);
		return session;
	}

	@Override
	public boolean exists(String mail, String token, String ipAddress) {
		if (!sessions.containsKey(token)) {
			return false;
		}

		LoginSession session = sessions.get(token);
		if (!session.mailAddress.equals(mail)) {
			return false;
		}

		if (!session.ipAddress.equals(ipAddress)) {
			return false;
		}

		return true;
	}

}
