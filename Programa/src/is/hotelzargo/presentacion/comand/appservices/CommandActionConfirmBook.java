package is.hotelzargo.presentacion.comand.appservices;

import is.hotelzargo.negocio.BusinessFactory;
import is.hotelzargo.negocio.Facade;
import is.hotelzargo.negocio.exception.BookAppServicesException;
import is.hotelzargo.presentacion.comand.Command;

public class CommandActionConfirmBook implements Command {

	private String id;
	
	public CommandActionConfirmBook(String id){
		this.id = id;
	}
	
	@Override
	public void execute() {
		
		Facade facade = BusinessFactory.getInstance().getFacade();
		
		try {
			facade.confirmBook(this.id);
		} catch (BookAppServicesException e) {
			e.printStackTrace();
		}
	}

}
