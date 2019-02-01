package com.example.testingpurposes.presenters;

public interface ILoginPresenter {
	public String registerUser(String username, String password, String repeatedPassword);
	public String loginUser(String username, String password);
}
