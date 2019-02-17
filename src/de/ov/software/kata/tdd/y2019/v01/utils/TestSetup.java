package de.ov.software.kata.tdd.y2019.v01.utils;

import de.ov.software.kata.tdd.y2019.v01.gateways.InMemoryLoginSessionGateway;
import de.ov.software.kata.tdd.y2019.v01.gateways.InMemoryUserGateway;

public class TestSetup {
	public static void setupContext() {
		Context.userGateway = new InMemoryUserGateway();
		Context.sessionGateway = new InMemoryLoginSessionGateway();
	}
}
