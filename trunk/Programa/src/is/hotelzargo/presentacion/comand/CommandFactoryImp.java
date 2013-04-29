package is.hotelzargo.presentacion.comand;

import is.hotelzargo.negocio.transfer.ClientTransfer;
import is.hotelzargo.negocio.transfer.ShiftTransfer;
import is.hotelzargo.presentacion.comand.appservices.CommandActionAddBook;
import is.hotelzargo.presentacion.comand.appservices.CommandActionAddClient;
import is.hotelzargo.presentacion.comand.appservices.CommandActionAddEmployee;
import is.hotelzargo.presentacion.comand.appservices.CommandActionAddRoom;
import is.hotelzargo.presentacion.comand.appservices.CommandActionAddService;
import is.hotelzargo.presentacion.comand.appservices.CommandActionAddShift;
import is.hotelzargo.presentacion.comand.appservices.CommandActionConfirmBook;
import is.hotelzargo.presentacion.comand.appservices.CommandActionDelBook;
import is.hotelzargo.presentacion.comand.appservices.CommandActionDelClient;
import is.hotelzargo.presentacion.comand.appservices.CommandActionDelEmployee;
import is.hotelzargo.presentacion.comand.appservices.CommandActionDelRoom;
import is.hotelzargo.presentacion.comand.appservices.CommandActionDelService;
import is.hotelzargo.presentacion.comand.appservices.CommandActionDelShift;
import is.hotelzargo.presentacion.comand.appservices.CommandActionError;
import is.hotelzargo.presentacion.comand.appservices.CommandActionFindBook;
import is.hotelzargo.presentacion.comand.appservices.CommandActionListBook;
import is.hotelzargo.presentacion.comand.appservices.CommandActionListClient;
import is.hotelzargo.presentacion.comand.appservices.CommandActionListEmployee;
import is.hotelzargo.presentacion.comand.appservices.CommandActionListRoom;
import is.hotelzargo.presentacion.comand.appservices.CommandActionListService;
import is.hotelzargo.presentacion.comand.appservices.CommandActionListShift;
import is.hotelzargo.presentacion.comand.appservices.CommandActionModBook;
import is.hotelzargo.presentacion.comand.appservices.CommandActionModClient;
import is.hotelzargo.presentacion.comand.appservices.CommandActionModEmployee;
import is.hotelzargo.presentacion.comand.appservices.CommandActionModRoom;
import is.hotelzargo.presentacion.comand.appservices.CommandActionModService;
import is.hotelzargo.presentacion.comand.appservices.CommandActionModShift;
import is.hotelzargo.presentacion.comand.gui.CommandActionShowBookFrame;
import is.hotelzargo.presentacion.comand.gui.CommandActionShowClientFrame;
import is.hotelzargo.presentacion.comand.gui.CommandActionShowEmployeeFrame;
import is.hotelzargo.presentacion.comand.gui.CommandActionShowRoomFrame;
import is.hotelzargo.presentacion.comand.gui.CommandActionShowServicesFrame;
import is.hotelzargo.presentacion.comand.gui.CommandActionShowShiftFrame;
import is.hotelzargo.presentacion.controller.Event;

public class CommandFactoryImp extends CommandFactory {

	@Override
	public Command createCommand(Event event, Object data) {
		
		switch (event){
		
		case SHOW_CLIENT_FRAME:
			return new CommandActionShowClientFrame((Boolean) data);
			
		case SHOW_ROOM_FRAME:
			return new CommandActionShowRoomFrame((Boolean) data);
		
		case SHOW_EMPLOYEE_FRAME:
			return new CommandActionShowEmployeeFrame((Boolean) data);
			
		case SHOW_BOOK_FRAME:
			return new CommandActionShowBookFrame((Boolean) data);
			
		case SHOW_SERVICES_FRAME:
			return new CommandActionShowServicesFrame((Boolean) data);
		
		case SHOW_SHIFT_FRAME:
			return new CommandActionShowShiftFrame((Boolean) data);
			
		case ADD_BOOK:
			return new CommandActionAddBook();
			
		case ADD_CLIENT:
			return new CommandActionAddClient((ClientTransfer) data);
			
		case ADD_EMPLOYEE:
			return new CommandActionAddEmployee();
			
		case ADD_ROOM:
			return new CommandActionAddRoom();
			
		case ADD_SERVICE:
			return new CommandActionAddService();
		
		case ADD_SHIFT:
			return new CommandActionAddShift((ShiftTransfer) data);
			
		case DELETE_BOOK:
			return new CommandActionDelBook();
			
		case DELETE_CLIENT:
			return new CommandActionDelClient((String) data);
			
		case DELETE_EMPLOYEE:
			return new CommandActionDelEmployee();
			
		case DELETE_ROOM:
			return new CommandActionDelRoom();
			
		case DELETE_SERVICE:
			return new CommandActionDelService();
			
		case DELETE_SHIFT:
			return new CommandActionDelShift();
			
		case LIST_BOOK:
			return new CommandActionListBook();
			
		case LIST_CLIENT:
			return new CommandActionListClient();
			
		case LIST_EMPLOYEE:
			return new CommandActionListEmployee();
			
		case LIST_ROOM:
			return new CommandActionListRoom();
			
		case LIST_SERVICE:
			return new CommandActionListService();
			
		case LIST_SHIFT:
			return new CommandActionListShift();
			
		case MOD_BOOK:
			return new CommandActionModBook();
			
		case MOD_CLIENT:
			return new CommandActionModClient();
			
		case MOD_EMPLOYEE:
			return new CommandActionModEmployee();
			
		case MOD_ROOM:
			return new CommandActionModRoom();
			
		case MOD_SERVICE:
			return new CommandActionModService();
			
		case MOD_SHIFT:
			return new CommandActionModShift();
			
		case FIND_BOOK:
			return new CommandActionFindBook();
			
		case CONFIRM_BOOK:
			return new CommandActionConfirmBook();
			
		case ERROR:
			return new CommandActionError();
			
		default:
			return null;
		}
	}
	
}
