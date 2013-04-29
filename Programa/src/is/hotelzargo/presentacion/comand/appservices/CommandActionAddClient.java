package is.hotelzargo.presentacion.comand.appservices;

import is.hotelzargo.negocio.BusinessFactory;
import is.hotelzargo.negocio.Facade;
import is.hotelzargo.negocio.exception.ClientAppServicesException;
import is.hotelzargo.negocio.transfer.ClientTransfer;
import is.hotelzargo.presentacion.comand.Command;

public class CommandActionAddClient implements Command {

	ClientTransfer clientTransfer;
	
	public CommandActionAddClient(ClientTransfer t){
		clientTransfer = t;
	}
	
	@Override
	public void execute() {
		
		Facade facade = BusinessFactory.getInstance().getFacade();
		
		try {
			facade.addClient(clientTransfer);
		} catch (ClientAppServicesException e) {
			e.printStackTrace();
			//Controller.getInstance().event(Event.ERROR,e.getMessage());
			
		}
	}

}
