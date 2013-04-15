package is.hotelzargo.presentacion.comand;

import is.hotelzargo.presentacion.controller.Event;

public abstract class CommandFactory {

	private static CommandFactory instance = null;
	
	public static CommandFactoryImp getInstance(){
		if(instance == null){
			instance = new CommandFactoryImp();
		}
		return (CommandFactoryImp)instance;
	}
	
	public abstract Command createCommand(Event event, Object data);
}
