import java.awt.EventQueue;
import ui.Navigator;

public class StartUp {

	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				try{
					
					new Navigator().startLogin();
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
	}
}