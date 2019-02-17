package de.ov.software.kata.tdd.y2019.v01.entities;

import java.time.LocalDateTime;

public class LoginSession {
	public String mailAddress;
	public String token;
	public String ipAddress;
	public LocalDateTime validFrom;
	public LocalDateTime validUntil;
}
