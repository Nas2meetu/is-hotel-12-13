package is.hotelzargo.presentacion.client.command.appservices;

import is.hotelzargo.negocio.exception.ClientAppServicesException;
import is.hotelzargo.negocio.facade.Facade;
import is.hotelzargo.negocio.factory.BusinessFactory;
import is.hotelzargo.presentacion.commandfactory.Command;

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
