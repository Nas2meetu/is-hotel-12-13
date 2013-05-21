package is.hotelzargo.presentacion.commandfactory;

import is.hotelzargo.negocio.book.transfer.BookTransfer;
import is.hotelzargo.negocio.client.transfer.ClientTransfer;
import is.hotelzargo.negocio.employee.transfer.EmployeeTransfer;
import is.hotelzargo.negocio.room.transfer.RoomTransfer;
import is.hotelzargo.negocio.service.transfer.ServiceTransfer;
import is.hotelzargo.negocio.shift.transfer.ShiftTransfer;
import is.hotelzargo.presentacion.book.command.appservices.CommandActionAddBook;
import is.hotelzargo.presentacion.book.command.appservices.CommandActionConfirmBook;
import is.hotelzargo.presentacion.book.command.appservices.CommandActionDelBook;
import is.hotelzargo.presentacion.book.command.appservices.CommandActionFindBook;
import is.hotelzargo.presentacion.book.command.appservices.CommandActionListBook;
import is.hotelzargo.presentacion.book.command.appservices.CommandActionModBook;
import is.hotelzargo.presentacion.book.command.gui.CommandActionShowBookFrame;
import is.hotelzargo.presentacion.client.command.appservices.CommandActionAddClient;
import is.hotelzargo.presentacion.client.command.appservices.CommandActionDelClient;
import is.hotelzargo.presentacion.client.command.appservices.CommandActionListClient;
import is.hotelzargo.presentacion.client.command.appservices.CommandActionModClient;
import is.hotelzargo.presentacion.client.command.gui.CommandActionShowClientFrame;
import is.hotelzargo.presentacion.commandfactory.error.CommandActionError;
import is.hotelzargo.presentacion.controller.Event;
import is.hotelzargo.presentacion.employee.command.appservices.CommandActionAddEmployee;
import is.hotelzargo.presentacion.employee.command.appservices.CommandActionDelEmployee;
import is.hotelzargo.presentacion.employee.command.appservices.CommandActionListEmployee;
import is.hotelzargo.presentacion.employee.command.appservices.CommandActionModEmployee;
import is.hotelzargo.presentacion.employee.command.gui.CommandActionShowEmployeeFrame;
import is.hotelzargo.presentacion.room.command.appservices.CommandActionAddRoom;
import is.hotelzargo.presentacion.room.command.appservices.CommandActionDelRoom;
import is.hotelzargo.presentacion.room.command.appservices.CommandActionListRoom;
import is.hotelzargo.presentacion.room.command.appservices.CommandActionModRoom;
import is.hotelzargo.presentacion.room.command.gui.CommandActionShowRoomFrame;
import is.hotelzargo.presentacion.service.command.appservices.CommandActionAddService;
import is.hotelzargo.presentacion.service.command.appservices.CommandActionDelService;
import is.hotelzargo.presentacion.service.command.appservices.CommandActionListService;
import is.hotelzargo.presentacion.service.command.appservices.CommandActionModService;
import is.hotelzargo.presentacion.service.command.gui.CommandActionShowServicesFrame;
import is.hotelzargo.presentacion.shift.command.appservices.CommandActionAddShift;
import is.hotelzargo.presentacion.shift.command.appservices.CommandActionDelShift;
import is.hotelzargo.presentacion.shift.command.appservices.CommandActionListShift;
import is.hotelzargo.presentacion.shift.command.appservices.CommandActionModShift;
import is.hotelzargo.presentacion.shift.command.gui.CommandActionShowShiftFrame;

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
			return new CommandActionDelBook((Integer) data);
			
		case DELETE_CLIENT:
			return new CommandActionDelClient((Integer) data);
			
		case DELETE_EMPLOYEE:
			return new CommandActionDelEmployee((Integer) data);
			
		case DELETE_ROOM:
			return new CommandActionDelRoom((Integer) data);
			
		case DELETE_SERVICE:
			return new CommandActionDelService((Integer) data);
			
		case DELETE_SHIFT:
			return new CommandActionDelShift((Integer) data);
			
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
			return new CommandActionFindBook((Integer) data);
			
		case CONFIRM_BOOK:
			return new CommandActionConfirmBook((Integer) data);
			
		case ERROR:
			return new CommandActionError((String) data);
			
		default:
			return null;
		}
	}
	
}
