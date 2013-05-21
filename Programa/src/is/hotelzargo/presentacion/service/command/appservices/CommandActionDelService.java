package is.hotelzargo.presentacion.service.command.appservices;

import is.hotelzargo.negocio.exception.ServicesAppServicesException;
import is.hotelzargo.negocio.facade.Facade;
import is.hotelzargo.negocio.factory.BusinessFactory;
import is.hotelzargo.presentacion.commandfactory.Command;
import is.hotelzargo.presentacion.controller.Controller;
import is.hotelzargo.presentacion.controller.Event;

public class CommandActionDelService implements Command {
	
	private int id;
	
	public CommandActionDelService(int id){
		this.id = id;
	}

	@Override
	public Object execute() {
		
		Facade facade = BusinessFactory.getInstance().getFacade();
		
		try {
			facade.delService(this.id);
		} catch (ServicesAppServicesException e) {
			e.printStackTrace();
			Controller.getInstance().event(Event.ERROR,e.getMessage(),null);
		}
		return null;
	}

}
