package is.hotelzargo.presentacion.gui;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public abstract class ClientFrame extends JFrame {

	private static ClientFrame instance = null;
	
	public static ClientFrame getInstance(){
		if(instance == null){
			instance = new ClientFrameImp();
		}
		return (ClientFrameImp) instance;
	}
}
