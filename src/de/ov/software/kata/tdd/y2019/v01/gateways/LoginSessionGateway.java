package de.ov.software.kata.tdd.y2019.v01.gateways;

import de.ov.software.kata.tdd.y2019.v01.entities.LoginSession;

public interface LoginSessionGateway {
	LoginSession save (LoginSession session);
	boolean exists (String mail, String token, String ipAddress);
}
