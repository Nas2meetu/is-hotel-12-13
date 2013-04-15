package is.hotelzargo.presentacion.gui;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public abstract class MainFrame extends JFrame {

	private static MainFrame instance = null;
	
	public static MainFrameImp getInstance(){
		if(instance == null){
			instance = new MainFrameImp();
		}
		return (MainFrameImp)instance;
	}
	
}
