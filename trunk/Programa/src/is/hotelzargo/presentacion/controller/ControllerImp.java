package is.hotelzargo.presentacion.controller;

import is.hotelzargo.presentacion.comand.CommandFactory;

public class ControllerImp extends Controller {

	@Override
	public void event(Event event, Object data){
		CommandFactory fac = CommandFactory.getInstance();
		fac.createCommand(event, data).execute();
	}
}
