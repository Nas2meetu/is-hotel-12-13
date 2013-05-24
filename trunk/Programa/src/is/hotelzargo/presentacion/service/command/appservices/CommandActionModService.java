package is.hotelzargo.presentacion.service.command.appservices;

import is.hotelzargo.negocio.exception.ServicesAppServicesException;
import is.hotelzargo.negocio.facade.Facade;
import is.hotelzargo.negocio.factory.BusinessFactory;
import is.hotelzargo.negocio.service.transfer.ServiceTransfer;
import is.hotelzargo.presentacion.commandfactory.Command;
import is.hotelzargo.presentacion.controller.Controller;
import is.hotelzargo.presentacion.controller.Event;

public class CommandActionModService implements Command {

	private ServiceTransfer serviceTransfer;
	
	public CommandActionModService(ServiceTransfer t){
		serviceTransfer = t;
	}
	
	@Override
	public Object execute() {
		
		Facade facade = BusinessFactory.getInstance().getFacade();
		
		try {
			facade.modService(this.serviceTransfer);
			Controller.getInstance().event(Event.MESSAGE,"Servicio modificado",null);
		} catch (ServicesAppServicesException e) {
			Controller.getInstance().event(Event.ERROR,e.getMessage(),null);
		}
		return null;
	}

}
