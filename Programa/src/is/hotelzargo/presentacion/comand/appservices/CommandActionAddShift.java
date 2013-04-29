package is.hotelzargo.presentacion.comand.appservices;

import is.hotelzargo.negocio.transfer.ShiftTransfer;
import is.hotelzargo.presentacion.comand.Command;

public class CommandActionAddShift implements Command {
	
	private ShiftTransfer shiftTransfer;
	
	public CommandActionAddShift(ShiftTransfer data){
		shiftTransfer = data;
	}

	@Override
	public void execute() {
		
	}
}
