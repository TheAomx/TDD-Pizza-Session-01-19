package de.ov.software.kata.tdd.y2019.v01.usecases.login.test;

import de.ov.software.kata.tdd.y2019.v01.usecases.login.LoginOutputBoundary;
import de.ov.software.kata.tdd.y2019.v01.usecases.login.LoginResponseModel;

public class LoginOutputBoundarySpy implements LoginOutputBoundary {

	public LoginResponseModel response;

	@Override
	public void present(LoginResponseModel response) {
		this.response = response;
	}

}
