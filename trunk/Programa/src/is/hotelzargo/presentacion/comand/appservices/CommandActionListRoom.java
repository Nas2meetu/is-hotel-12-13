package is.hotelzargo.presentacion.comand.appservices;

import is.hotelzargo.negocio.BusinessFactory;
import is.hotelzargo.negocio.Facade;
import is.hotelzargo.negocio.exception.RoomAppServicesException;
import is.hotelzargo.presentacion.comand.Command;

public class CommandActionListRoom implements Command {

	@Override
	public void execute() {
		
		Facade facade = BusinessFactory.getInstance().getFacade();
		
		try {
			facade.listRoom();
		} catch (RoomAppServicesException e) {
			e.printStackTrace();
		}
	}

}
