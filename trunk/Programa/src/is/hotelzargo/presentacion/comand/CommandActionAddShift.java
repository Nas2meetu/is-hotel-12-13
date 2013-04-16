package is.hotelzargo.presentacion.comand;

import is.hotelzargo.negocio.transfer.ShiftTransfer;

public class CommandActionAddShift implements Command {
	
	private ShiftTransfer shiftTransfer;
	
	public CommandActionAddShift(ShiftTransfer data){
		shiftTransfer = data;
	}

	@Override
	public void execute() {
		
	}
}
