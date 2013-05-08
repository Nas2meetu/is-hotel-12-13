package is.hotelzargo.presentacion.comand.appservices;

import is.hotelzargo.negocio.BusinessFactory;
import is.hotelzargo.negocio.Facade;
import is.hotelzargo.negocio.exception.ServicesAppServicesException;
import is.hotelzargo.presentacion.comand.Command;

public class CommandActionListService implements Command {

	@Override
	public void execute() {
		
		Facade facade = BusinessFactory.getInstance().getFacade();
		
		try {
			facade.listService();
		} catch (ServicesAppServicesException e) {
			e.printStackTrace();
		}
	}

}
