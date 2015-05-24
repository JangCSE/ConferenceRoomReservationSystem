package login;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class LoginView extends JPanel implements Observer {

	public LoginView() {
		
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		System.out.println(((LoginModel)arg0).getMessage());
	}

}