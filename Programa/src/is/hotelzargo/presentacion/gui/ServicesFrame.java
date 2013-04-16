package is.hotelzargo.presentacion.gui;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class ServicesFrame extends JFrame {

	private static ServicesFrame instance = null;
	
	public static ServicesFrame getInstance(){
		if(instance == null){
			instance = new ServicesFrameImp();
		}
		return (ServicesFrameImp) instance;
	}
}
