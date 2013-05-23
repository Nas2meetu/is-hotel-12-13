package is.hotelzargo.negocio.facade;

import java.sql.Date;
import java.util.Vector;

import is.hotelzargo.negocio.book.appservice.BookAppServices;
import is.hotelzargo.negocio.book.transfer.BookTransfer;
import is.hotelzargo.negocio.client.appservice.ClientAppServices;
import is.hotelzargo.negocio.client.transfer.ClientTransfer;
import is.hotelzargo.negocio.employee.appservice.EmployeeAppServices;
import is.hotelzargo.negocio.employee.transfer.EmployeeTransfer;
import is.hotelzargo.negocio.exception.BookAppServicesException;
import is.hotelzargo.negocio.exception.ClientAppServicesException;
import is.hotelzargo.negocio.exception.EmployeeAppServicesException;
import is.hotelzargo.negocio.exception.RoomAppServicesException;
import is.hotelzargo.negocio.exception.ServicesAppServicesException;
import is.hotelzargo.negocio.exception.ShiftAppServicesException;
import is.hotelzargo.negocio.factory.BusinessFactory;
import is.hotelzargo.negocio.room.appservice.RoomAppServices;
import is.hotelzargo.negocio.room.transfer.RoomTransfer;
import is.hotelzargo.negocio.service.appservice.ServicesAppServices;
import is.hotelzargo.negocio.service.transfer.ServiceTransfer;
import is.hotelzargo.negocio.shift.appservice.ShiftAppServices;
import is.hotelzargo.negocio.shift.transfer.ShiftTransfer;

public class FacadeImp implements Facade {

	@Override
	public void addClient(ClientTransfer t)throws ClientAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		ClientAppServices clientAS = fac.getClientAS();
		clientAS.addClient(t);
	}

	@Override
	public void delClient(int id) throws ClientAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		ClientAppServices clientAS = fac.getClientAS();
		clientAS.delClient(id);
	}

	@Override
	public Vector<ClientTransfer> listClient() throws ClientAppServicesException {

		BusinessFactory fac = BusinessFactory.getInstance();
		ClientAppServices clientAS = fac.getClientAS();
		return clientAS.listClient();
	}

	@Override
	public void modClient(ClientTransfer t) throws ClientAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		ClientAppServices clientAS = fac.getClientAS();
		clientAS.modClient(t);
	}

	@Override
	public void addBook(BookTransfer t) throws BookAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		BookAppServices bookAS = fac.getBookAS();
		bookAS.addBook(t);
	}

	@Override
	public void delBook(int id) throws BookAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		BookAppServices bookAS = fac.getBookAS();
		bookAS.delBook(id);
	}

	@Override
	public Vector<BookTransfer> listBook() throws BookAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		BookAppServices bookAS = fac.getBookAS();
		return bookAS.listBook();
	}

	@Override
	public void modBook(BookTransfer t) throws BookAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		BookAppServices bookAS = fac.getBookAS();
		bookAS.modBook(t);
	}

	@Override
	public Vector<Integer> findBook(String checkIn, String checkOut)
			throws BookAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		BookAppServices bookAS = fac.getBookAS();
		return bookAS.findBook(checkIn, checkOut);
	}

	@Override
	public void confirmBook(int id) throws BookAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		BookAppServices bookAS = fac.getBookAS();
		bookAS.confirmBook(id);
	}

	@Override
	public void addEmployee(EmployeeTransfer t) throws EmployeeAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		EmployeeAppServices employeeAS = fac.getEmployeeAS();
		employeeAS.addEmployee(t);
	}

	@Override
	public void delEmployee(int id) throws EmployeeAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		EmployeeAppServices employeeAS = fac.getEmployeeAS();
		employeeAS.delEmployee(id);
	}

	@Override
	public Vector<EmployeeTransfer> listEmployee() throws EmployeeAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		EmployeeAppServices employeeAS = fac.getEmployeeAS();
		return employeeAS.listEmployee();
	}

	@Override
	public void modEmployee(EmployeeTransfer t) throws EmployeeAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		EmployeeAppServices employeeAS = fac.getEmployeeAS();
		employeeAS.modEmployee(t);
	}

	@Override
	public void addRoom(RoomTransfer t) throws RoomAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		RoomAppServices roomAS = fac.getRoomAS();
		roomAS.addRoom(t);
	}

	@Override
	public void delRoom(int id) throws RoomAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		RoomAppServices roomAS = fac.getRoomAS();
		roomAS.delRoom(id);
	}

	@Override
	public Vector<RoomTransfer> listRoom() throws RoomAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		RoomAppServices roomAS = fac.getRoomAS();
		return roomAS.listRoom();
	}

	@Override
	public void modRoom(RoomTransfer t) throws RoomAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		RoomAppServices roomAS = fac.getRoomAS();
		roomAS.modRoom(t);
	}

	@Override
	public void addService(ServiceTransfer t) throws ServicesAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		ServicesAppServices servicesAS = fac.getServicesAS();
		servicesAS.addService(t);
	}

	@Override
	public void delService(int id) throws ServicesAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		ServicesAppServices servicesAS = fac.getServicesAS();
		servicesAS.delService(id);
	}

	@Override
	public Vector<ServiceTransfer> listService() throws ServicesAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		ServicesAppServices servicesAS = fac.getServicesAS();
		return servicesAS.listService();
	}

	@Override
	public void modService(ServiceTransfer t) throws ServicesAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		ServicesAppServices servicesAS = fac.getServicesAS();
		servicesAS.modService(t);
	}

	@Override
	public void addShift(Vector<String> t) throws ShiftAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		ShiftAppServices shiftAS = fac.getShiftAS();
		shiftAS.addShift(t);
	}

	@Override
	public void delShift(int id) throws ShiftAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		ShiftAppServices shiftAS = fac.getShiftAS();
		shiftAS.deleteShift(id);
	}

	@Override
	public Vector<ShiftTransfer> listShift() throws ShiftAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		ShiftAppServices shiftAS = fac.getShiftAS();
		return shiftAS.listShift();
	}

	@Override
	public void modShift(ShiftTransfer t) throws ShiftAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		ShiftAppServices shiftAS = fac.getShiftAS();
		shiftAS.modShift(t);
	}

}
