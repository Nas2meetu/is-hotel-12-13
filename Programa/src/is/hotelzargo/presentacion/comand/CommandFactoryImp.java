package is.hotelzargo.presentacion.comand;

import is.hotelzargo.negocio.transfer.BookTransfer;
import is.hotelzargo.negocio.transfer.ClientTransfer;
import is.hotelzargo.negocio.transfer.EmployeeTransfer;
import is.hotelzargo.negocio.transfer.RoomTransfer;
import is.hotelzargo.negocio.transfer.ServiceTransfer;
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
			return new CommandActionAddBook((BookTransfer) data);
			
		case ADD_CLIENT:
			return new CommandActionAddClient((ClientTransfer) data);
			
		case ADD_EMPLOYEE:
			return new CommandActionAddEmployee((EmployeeTransfer)data);
			
		case ADD_ROOM:
			return new CommandActionAddRoom((RoomTransfer) data);
			
		case ADD_SERVICE:
			return new CommandActionAddService((ServiceTransfer) data);
		
		case ADD_SHIFT:
			return new CommandActionAddShift((ShiftTransfer) data);
			
		case DELETE_BOOK:
			return new CommandActionDelBook((String) data);
			
		case DELETE_CLIENT:
			return new CommandActionDelClient((String) data);
			
		case DELETE_EMPLOYEE:
			return new CommandActionDelEmployee((String) data);
			
		case DELETE_ROOM:
			return new CommandActionDelRoom((String) data);
			
		case DELETE_SERVICE:
			return new CommandActionDelService((String) data);
			
		case DELETE_SHIFT:
			return new CommandActionDelShift((String) data);
			
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
			return new CommandActionModBook((BookTransfer) data);
			
		case MOD_CLIENT:
			return new CommandActionModClient((ClientTransfer) data);
			
		case MOD_EMPLOYEE:
			return new CommandActionModEmployee((EmployeeTransfer) data);
			
		case MOD_ROOM:
			return new CommandActionModRoom((RoomTransfer) data);
			
		case MOD_SERVICE:
			return new CommandActionModService((ServiceTransfer) data);
			
		case MOD_SHIFT:
			return new CommandActionModShift((ShiftTransfer) data);
			
		case FIND_BOOK:
			return new CommandActionFindBook((String) data);
			
		case CONFIRM_BOOK:
			return new CommandActionConfirmBook((String) data);
			
		case ERROR:
			return new CommandActionError();
			
		default:
			return null;
		}
	}
	
}
