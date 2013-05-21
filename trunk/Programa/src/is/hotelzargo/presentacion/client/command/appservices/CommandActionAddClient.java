package is.hotelzargo.presentacion.client.command.appservices;

import is.hotelzargo.negocio.client.transfer.ClientTransfer;
import is.hotelzargo.negocio.exception.ClientAppServicesException;
import is.hotelzargo.negocio.facade.Facade;
import is.hotelzargo.negocio.factory.BusinessFactory;
import is.hotelzargo.presentacion.commandfactory.Command;
import is.hotelzargo.presentacion.controller.Controller;
import is.hotelzargo.presentacion.controller.Event;

public class CommandActionAddClient implements Command {

	ClientTransfer clientTransfer;
	
	public CommandActionAddClient(ClientTransfer t){
		clientTransfer = t;
	}
	
	@Override
	public Object execute() {
		
		Facade facade = BusinessFactory.getInstance().getFacade();
		
		try {
			facade.addClient(clientTransfer);
		} catch (ClientAppServicesException e) {
			//e.printStackTrace();
			//TODO hacer en el resto
			Controller.getInstance().event(Event.ERROR,e.getMessage(),null);
		}
		return null;
	}

}
