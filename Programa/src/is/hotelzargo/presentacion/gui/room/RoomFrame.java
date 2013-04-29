package is.hotelzargo.presentacion.gui.room;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public abstract class RoomFrame extends JFrame {
	
	private static RoomFrame instance = null;
	
	public static RoomFrame getInstance(){
		if(instance == null){
			instance = new RoomFrameImp();
		}
		return (RoomFrameImp) instance;
	}
}
