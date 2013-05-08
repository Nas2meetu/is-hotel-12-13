package is.hotelzargo.presentacion.comand.appservices;

import is.hotelzargo.negocio.BusinessFactory;
import is.hotelzargo.negocio.Facade;
import is.hotelzargo.negocio.exception.BookAppServicesException;
import is.hotelzargo.negocio.transfer.BookTransfer;
import is.hotelzargo.presentacion.comand.Command;

public class CommandActionModBook implements Command {

	private BookTransfer bookTransfer;
	
	public CommandActionModBook(BookTransfer t){
		bookTransfer = t;
	}
	
	@Override
	public void execute() {
		
		Facade facade = BusinessFactory.getInstance().getFacade();
		
		try {
			facade.modBook(this.bookTransfer);
		} catch (BookAppServicesException e) {
			e.printStackTrace();
		}
	}

}
