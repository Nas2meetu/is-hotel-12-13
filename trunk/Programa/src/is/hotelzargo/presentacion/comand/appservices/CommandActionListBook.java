package is.hotelzargo.presentacion.comand.appservices;

import is.hotelzargo.negocio.BusinessFactory;
import is.hotelzargo.negocio.Facade;
import is.hotelzargo.negocio.exception.BookAppServicesException;
import is.hotelzargo.presentacion.comand.Command;

public class CommandActionListBook implements Command {

	@Override
	public void execute() {
		
		Facade facade = BusinessFactory.getInstance().getFacade();
		
		try {
			facade.listBook();
		} catch (BookAppServicesException e) {
			e.printStackTrace();
		}
	}

}
