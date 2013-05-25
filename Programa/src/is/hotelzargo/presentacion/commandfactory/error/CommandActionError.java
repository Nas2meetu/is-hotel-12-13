package is.hotelzargo.presentacion.commandfactory.error;

import javax.swing.JOptionPane;

import is.hotelzargo.presentacion.commandfactory.Command;

public class CommandActionError implements Command {

	private String message;
	
	public CommandActionError(String message){
		this.message = message;
	}
	
	@Override
	public Object execute() {
		
		JOptionPane.showMessageDialog(null, message);
		
		return null;
	}

}
