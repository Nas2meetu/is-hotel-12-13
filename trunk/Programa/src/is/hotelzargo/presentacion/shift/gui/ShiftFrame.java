package is.hotelzargo.presentacion.shift.gui;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public abstract class ShiftFrame extends JFrame {

	private static ShiftFrame instance = null;
	
	public static ShiftFrame getInstance(){
		if(instance == null){
			instance = new ShiftFrameImp();
		}
		return (ShiftFrameImp) instance;
	}
}
