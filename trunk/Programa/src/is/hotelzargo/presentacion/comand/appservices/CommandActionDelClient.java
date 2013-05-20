package is.hotelzargo.presentacion.comand.appservices;

import is.hotelzargo.negocio.BusinessFactory;
import is.hotelzargo.negocio.Facade;
import is.hotelzargo.negocio.exception.ClientAppServicesException;
import is.hotelzargo.presentacion.comand.Command;

public class CommandActionDelClient implements Command {

	int id;
	
	public CommandActionDelClient(int data) {
		id = data;
	}

	@Override
	public Object execute() {
		Facade facade = BusinessFactory.getInstance().getFacade();
		
		try {
			facade.delClient(id);
		} catch (ClientAppServicesException e) {
			e.printStackTrace();
			//Controller.getInstance().event(Event.ERROR,e.getMessage());
			
		}
		return null;
	}

}
