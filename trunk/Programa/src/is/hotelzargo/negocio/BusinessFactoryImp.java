package is.hotelzargo.negocio;

import is.hotelzargo.negocio.appservices.BookAppServices;
import is.hotelzargo.negocio.appservices.BookAppServicesImp;
import is.hotelzargo.negocio.appservices.ClientAppServices;
import is.hotelzargo.negocio.appservices.ClientAppServicesImp;
import is.hotelzargo.negocio.appservices.EmployeeAppServices;
import is.hotelzargo.negocio.appservices.EmployeeAppServicesImp;
import is.hotelzargo.negocio.appservices.RoomAppServices;
import is.hotelzargo.negocio.appservices.RoomAppServicesImp;
import is.hotelzargo.negocio.appservices.ServicesAppServices;
import is.hotelzargo.negocio.appservices.ServicesAppServicesImp;
import is.hotelzargo.negocio.appservices.ShiftAppServices;
import is.hotelzargo.negocio.appservices.ShiftAppServicesImp;

public class BusinessFactoryImp extends BusinessFactory {

	@Override
	public Facade getFacade() {
		return new FacadeImp();
	}
	
	@Override
	public ClientAppServices getClientAS() {
		return new ClientAppServicesImp();
	}

	@Override
	public BookAppServices getBookAS() {
		return new BookAppServicesImp();
	}

	@Override
	public EmployeeAppServices getEmployeeAS() {
		return new EmployeeAppServicesImp();
	}

	@Override
	public RoomAppServices getRoomAS() {
		return new RoomAppServicesImp();
	}

	@Override
	public ServicesAppServices getServicesAS() {
		return new ServicesAppServicesImp();
	}

	@Override
	public ShiftAppServices getShiftAS() {
		return new ShiftAppServicesImp();
	}

	
}
