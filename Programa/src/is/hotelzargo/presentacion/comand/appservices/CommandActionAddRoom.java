package is.hotelzargo.presentacion.comand.appservices;

import is.hotelzargo.negocio.BusinessFactory;
import is.hotelzargo.negocio.Facade;
import is.hotelzargo.negocio.exception.RoomAppServicesException;
import is.hotelzargo.negocio.transfer.RoomTransfer;
import is.hotelzargo.presentacion.comand.Command;

public class CommandActionAddRoom implements Command {

	private RoomTransfer roomTransfer;
	
	public CommandActionAddRoom(RoomTransfer t) {
		roomTransfer = t;
	}
	
	@Override
	public void execute() {
		Facade facade = BusinessFactory.getInstance().getFacade();
		
		try {
			facade.addRoom(roomTransfer);
		} catch (RoomAppServicesException e) {
			e.printStackTrace();
			//Controller.getInstance().event(Event.ERROR,e.getMessage());
			
		}
	}

}
