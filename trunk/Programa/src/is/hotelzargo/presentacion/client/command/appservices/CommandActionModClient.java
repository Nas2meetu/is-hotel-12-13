package is.hotelzargo.presentacion.client.command.appservices;

import is.hotelzargo.negocio.client.transfer.ClientTransfer;
import is.hotelzargo.negocio.exception.ClientAppServicesException;
import is.hotelzargo.negocio.facade.Facade;
import is.hotelzargo.negocio.factory.BusinessFactory;
import is.hotelzargo.presentacion.commandfactory.Command;
import is.hotelzargo.presentacion.controller.Controller;
import is.hotelzargo.presentacion.controller.Event;

public class CommandActionModClient implements Command {

	private ClientTransfer clientTransfer;
	
	public CommandActionModClient(ClientTransfer t){
		clientTransfer = t;
	}
	
	@Override
	public Object execute() {
		
		Facade facade = BusinessFactory.getInstance().getFacade();
		
		try {
			facade.modClient(this.clientTransfer);
			Controller.getInstance().event(Event.MESSAGE,"Cliente modificado",null);
		} catch (ClientAppServicesException e) {
			Controller.getInstance().event(Event.ERROR,e.getMessage(),null);
		}
		return null;
	}

}
