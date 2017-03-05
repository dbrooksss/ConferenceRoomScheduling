package ui;

import model.User;

public class Navigator implements Navigate{
	private Login login;
	private User user;

	public void startLogin(){
		login = new Login(this);
		login.setVisible(true);
	}

	public void closeLogin(){
		user = login.getUser();
		login.setVisible(false);
		login.dispose();
	}

	@Override
	public void startConferenceRoom(){
		closeLogin();
		ConferenceRoomSelector window = new ConferenceRoomSelector(user);
		window.setVisible(true);

		
	}
}
