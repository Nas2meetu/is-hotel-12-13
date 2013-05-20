package is.hotelzargo.presentacion.controller;

import is.hotelzargo.presentacion.comand.CommandFactory;

public class ControllerImp extends Controller {

	@Override
	public Object event(Event event, Object data,Object returnData){
		CommandFactory fac = CommandFactory.getInstance();
		returnData = fac.createCommand(event, data).execute();

		return returnData;

	}
}
