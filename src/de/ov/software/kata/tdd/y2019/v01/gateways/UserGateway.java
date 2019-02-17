package de.ov.software.kata.tdd.y2019.v01.gateways;

import java.util.Optional;

import de.ov.software.kata.tdd.y2019.v01.entities.User;

public interface UserGateway {
	User save(User user);

	void delete(User user);

	Optional<User> findUserByMail(String mail);

	int getTotalNumberOfUsers();
}
