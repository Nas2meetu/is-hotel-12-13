package is.hotelzargo.presentacion.room.command.appservices;

import is.hotelzargo.negocio.exception.RoomAppServicesException;
import is.hotelzargo.negocio.facade.Facade;
import is.hotelzargo.negocio.factory.BusinessFactory;
import is.hotelzargo.presentacion.commandfactory.Command;
import is.hotelzargo.presentacion.controller.Controller;
import is.hotelzargo.presentacion.controller.Event;

public class CommandActionDelRoom implements Command {
	
	private int id;

	public CommandActionDelRoom(int id){
		this.id = id;
	}
	
	@Override
	public Object execute() {
		
		Facade facade = BusinessFactory.getInstance().getFacade();
		
		try {
			facade.delRoom(this.id);
			Controller.getInstance().event(Event.MESSAGE,"Habitacion eliminado",null);
		} catch (RoomAppServicesException e) {
			Controller.getInstance().event(Event.ERROR,e.getMessage(),null);
		}
		return null;
	}

}
