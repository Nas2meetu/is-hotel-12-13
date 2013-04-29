package is.hotelzargo.presentacion.gui.shift;

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
