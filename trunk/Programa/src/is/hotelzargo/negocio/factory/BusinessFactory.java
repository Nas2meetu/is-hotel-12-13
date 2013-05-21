package is.hotelzargo.negocio.factory;

import is.hotelzargo.negocio.book.appservice.BookAppServices;
import is.hotelzargo.negocio.client.appservice.ClientAppServices;
import is.hotelzargo.negocio.employee.appservice.EmployeeAppServices;
import is.hotelzargo.negocio.facade.Facade;
import is.hotelzargo.negocio.room.appservice.RoomAppServices;
import is.hotelzargo.negocio.service.appservice.ServicesAppServices;
import is.hotelzargo.negocio.shift.appservice.ShiftAppServices;

public abstract class BusinessFactory {

	private static BusinessFactory instance = null;
	
	public static BusinessFactory getInstance(){
		if(instance == null){
			instance = new BusinessFactoryImp();
		}
		return (BusinessFactoryImp) instance;
	}
	
	public abstract Facade getFacade();
	
	public abstract ClientAppServices getClientAS();
	
	public abstract BookAppServices getBookAS();
	
	public abstract EmployeeAppServices getEmployeeAS();
	
	public abstract RoomAppServices getRoomAS();
	
	public abstract ServicesAppServices getServicesAS();
	
	public abstract ShiftAppServices getShiftAS();
}
