package ui;

import javax.swing.JFrame;
import javax.swing.JTextField;
import data.UserRepository;
import model.User;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends BaseJFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTextField txtUsername;
	private JTextField passwordField;
	private User user;
	private Navigate start;

	public Login(Navigate start){
		this.start = start;
		initialize();
	}

	private void initialize(){
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
			
		createLabel("Login:", 200, 21, 86, 25, font14);
		createLabel("Username:", 91, 72, 95, 14, font12);

		txtUsername = new JTextField("");
		txtUsername.setBounds(172, 69, 153, 20);
		getContentPane().add(txtUsername);
		txtUsername.setColumns(10);

		createLabel("Password:", 91, 100, 95, 14, font12);
		
		passwordField = new JPasswordField("");
		// passwordField.setEchoChar('•');
		passwordField.setBounds(172, 100, 153, 20);
		getContentPane().add(passwordField);

		createLabel("Forgot Username?", 239, 131, 86, 14, font10);
		createLabel("Forgot Password?", 239, 150, 86, 14, font10);

		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.addActionListener(this);
		btnSignIn.setBounds(177, 196, 89, 23);
		getContentPane().add(btnSignIn);

		JSeparator separator = new JSeparator();
		separator.setBounds(126, 237, 188, 2);
		getContentPane().add(separator);
	}

	@Override
	public void actionPerformed(ActionEvent e){
		String username = txtUsername.getText();
		String password = String.valueOf(((JPasswordField) passwordField).getPassword());

		UserRepository repository = new UserRepository();
		user = repository.login(username, password);

		if(user != null){
			start.startConferenceRoom();

		}else{
			System.out.println("Invalid Login");
		}
	}
	
	public User getUser(){
		return user;
	}
}