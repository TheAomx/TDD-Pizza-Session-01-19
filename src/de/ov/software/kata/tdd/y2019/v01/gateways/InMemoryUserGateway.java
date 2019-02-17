package de.ov.software.kata.tdd.y2019.v01.gateways;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import de.ov.software.kata.tdd.y2019.v01.entities.User;

public class InMemoryUserGateway implements UserGateway {

	Map<String, User> users = new HashMap<>();

	@Override
	public User save(User user) {
		users.put(user.mailAddress, user);
		return user;
	}

	@Override
	public void delete(User user) {
		users.remove(user.mailAddress);
	}

	@Override
	public Optional<User> findUserByMail(String mail) {
		return Optional.ofNullable(users.get(mail));
	}

	@Override
	public int getTotalNumberOfUsers() {
		return users.size();
	}

}
