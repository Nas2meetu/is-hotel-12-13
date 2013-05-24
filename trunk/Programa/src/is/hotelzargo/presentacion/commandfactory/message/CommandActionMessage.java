package is.hotelzargo.presentacion.commandfactory.message;

import is.hotelzargo.presentacion.commandfactory.Command;

import javax.swing.JOptionPane;

public class CommandActionMessage implements Command {

	private String message;
	
	public CommandActionMessage(String message){
		this.message = message;
	}
	
	@Override
	public Object execute() {
		
		JOptionPane.showMessageDialog(null, message);
		
		return null;
	}

}
