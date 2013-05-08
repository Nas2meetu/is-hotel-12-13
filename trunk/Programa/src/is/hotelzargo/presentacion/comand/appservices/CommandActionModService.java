package is.hotelzargo.presentacion.comand.appservices;

import is.hotelzargo.negocio.BusinessFactory;
import is.hotelzargo.negocio.Facade;
import is.hotelzargo.negocio.exception.ServicesAppServicesException;
import is.hotelzargo.negocio.transfer.ServiceTransfer;
import is.hotelzargo.presentacion.comand.Command;

public class CommandActionModService implements Command {

	private ServiceTransfer serviceTransfer;
	
	public CommandActionModService(ServiceTransfer t){
		serviceTransfer = t;
	}
	
	@Override
	public void execute() {
		
		Facade facade = BusinessFactory.getInstance().getFacade();
		
		try {
			facade.modService(this.serviceTransfer);
		} catch (ServicesAppServicesException e) {
			e.printStackTrace();
		}
	}

}
