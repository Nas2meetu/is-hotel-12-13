package is.hotelzargo.presentacion.client.command.appservices;

import is.hotelzargo.negocio.client.transfer.ClientTransfer;
import is.hotelzargo.negocio.exception.ClientAppServicesException;
import is.hotelzargo.negocio.facade.Facade;
import is.hotelzargo.negocio.factory.BusinessFactory;
import is.hotelzargo.presentacion.commandfactory.Command;

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
		} catch (ClientAppServicesException e) {
			e.printStackTrace();
		}
		return null;
	}

}
