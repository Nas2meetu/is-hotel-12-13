package is.hotelzargo.presentacion.comand.appservices;

import is.hotelzargo.negocio.BusinessFactory;
import is.hotelzargo.negocio.Facade;
import is.hotelzargo.negocio.exception.BookAppServicesException;
import is.hotelzargo.presentacion.comand.Command;

public class CommandActionDelBook implements Command {

	private int id;
	
	public CommandActionDelBook(int id)
	{
		this.id = id;
	}
	
	@Override
	public Object execute() {
		
		Facade facade = BusinessFactory.getInstance().getFacade();
		
		try {
			facade.delBook(this.id);
		} catch (BookAppServicesException e) {
			e.printStackTrace();
		}
	}

}
