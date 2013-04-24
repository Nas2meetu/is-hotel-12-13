package is.hotelzargo.presentacion.comand;

import is.hotelzargo.negocio.BusinessFactory;
import is.hotelzargo.negocio.Facade;
import is.hotelzargo.negocio.exception.ClientAppServiceException;

public class CommandActionListClient implements Command {

	@Override
	public void execute() {
		Facade facade = BusinessFactory.getInstance().getFacade();
		
		try {
			facade.listClient();
		} catch (ClientAppServiceException e) {
			e.printStackTrace();
			//Controller.getInstance().event(Event.ERROR,e.getMessage());
			
		}
	}

}
