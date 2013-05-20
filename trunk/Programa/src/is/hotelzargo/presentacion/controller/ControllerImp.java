package is.hotelzargo.presentacion.controller;

import is.hotelzargo.presentacion.comand.CommandFactory;

public class ControllerImp extends Controller {

	@Override
	public Object event(Event event, Object data,Object returnData){
		CommandFactory fac = CommandFactory.getInstance();
		returnData = fac.createCommand(event, data).execute();

		if (returnData ==null){
			System.out.println("data es null");
		}
		else{
			System.out.println("Data NOOOO es null");
		}

		return returnData;

	}
}
