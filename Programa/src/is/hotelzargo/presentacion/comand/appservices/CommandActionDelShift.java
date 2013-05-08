package is.hotelzargo.presentacion.comand.appservices;

import is.hotelzargo.negocio.BusinessFactory;
import is.hotelzargo.negocio.Facade;
import is.hotelzargo.negocio.exception.ShiftAppServicesException;
import is.hotelzargo.presentacion.comand.Command;

public class CommandActionDelShift implements Command {
	
	private String id;
	
	public CommandActionDelShift(String id){
		this.id = id;
	}

	@Override
	public void execute() {
		
		Facade facade = BusinessFactory.getInstance().getFacade();
		
		try {
			facade.delShift(this.id);
		} catch (ShiftAppServicesException e) {
			e.printStackTrace();
		}
	}

}
