package de.ov.software.kata.tdd.y2019.v01.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class User {
	public String username;
	public String passwordHash;
	public String mailAddress;
	public LocalDate registeredAt;
	public LocalDateTime lastSeen;
	public String firstName;
	public String lastName;
}
