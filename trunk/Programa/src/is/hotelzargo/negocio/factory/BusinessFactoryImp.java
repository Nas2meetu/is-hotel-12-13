package is.hotelzargo.negocio.factory;

import is.hotelzargo.negocio.book.appservice.BookAppServices;
import is.hotelzargo.negocio.book.appservice.BookAppServicesImp;
import is.hotelzargo.negocio.client.appservice.ClientAppServices;
import is.hotelzargo.negocio.client.appservice.ClientAppServicesImp;
import is.hotelzargo.negocio.employee.appservice.EmployeeAppServices;
import is.hotelzargo.negocio.employee.appservice.EmployeeAppServicesImp;
import is.hotelzargo.negocio.facade.Facade;
import is.hotelzargo.negocio.facade.FacadeImp;
import is.hotelzargo.negocio.room.appservice.RoomAppServices;
import is.hotelzargo.negocio.room.appservice.RoomAppServicesImp;
import is.hotelzargo.negocio.service.appservice.ServicesAppServices;
import is.hotelzargo.negocio.service.appservice.ServicesAppServicesImp;
import is.hotelzargo.negocio.shift.appservice.ShiftAppServices;
import is.hotelzargo.negocio.shift.appservice.ShiftAppServicesImp;

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
