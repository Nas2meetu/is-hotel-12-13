package is.hotelzargo.presentacion.gui;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public abstract class BookFrame extends JFrame {

	private static BookFrame instance = null;
	
	public static BookFrame getInstance(){
		if(instance == null){
			instance = new BookFrameImp();
		}
		return (BookFrameImp) instance;
	}
}
