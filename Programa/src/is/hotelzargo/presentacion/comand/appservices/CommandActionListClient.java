package is.hotelzargo.presentacion.comand.appservices;

import is.hotelzargo.negocio.BusinessFactory;
import is.hotelzargo.negocio.Facade;
import is.hotelzargo.negocio.exception.ClientAppServicesException;
import is.hotelzargo.presentacion.comand.Command;

public class CommandActionListClient implements Command {

	@Override
	public Object execute() {
		Facade facade = BusinessFactory.getInstance().getFacade();
		
		try {
			Object obj = facade.listClient();
			if(obj == null){
				System.out.println("Es null");
			}
			else{
				System.out.println("NO Es null");
				
			}
			
			return obj;			
		} catch (ClientAppServicesException e) {
			//e.printStackTrace();
			//Controller.getInstance().event(Event.ERROR,e.getMessage());
			System.out.println("en el catch del execute facade list");
			
		}
		return null;
	}

}
