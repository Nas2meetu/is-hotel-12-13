package is.hotelzargo.negocio;

import is.hotelzargo.negocio.appservices.BookAppServices;
import is.hotelzargo.negocio.appservices.ClientAppServices;
import is.hotelzargo.negocio.appservices.EmployeeAppServices;
import is.hotelzargo.negocio.appservices.RoomAppServices;
import is.hotelzargo.negocio.appservices.ServicesAppServices;
import is.hotelzargo.negocio.appservices.ShiftAppServices;

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
