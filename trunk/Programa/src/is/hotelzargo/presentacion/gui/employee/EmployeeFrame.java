package is.hotelzargo.presentacion.gui.employee;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public abstract class EmployeeFrame extends JFrame {

	private static EmployeeFrame instance = null;
	
	public static EmployeeFrame getInstance(){
		if(instance == null){
			instance = new EmployeeFrameImp();
		}
		return (EmployeeFrameImp) instance;
	}
}
