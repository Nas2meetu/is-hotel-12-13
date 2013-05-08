package is.hotelzargo.negocio;

import is.hotelzargo.negocio.appservices.BookAppServices;
import is.hotelzargo.negocio.appservices.ClientAppServices;
import is.hotelzargo.negocio.appservices.EmployeeAppServices;
import is.hotelzargo.negocio.appservices.RoomAppServices;
import is.hotelzargo.negocio.appservices.ServicesAppServices;
import is.hotelzargo.negocio.appservices.ShiftAppServices;
import is.hotelzargo.negocio.exception.BookAppServicesException;
import is.hotelzargo.negocio.exception.ClientAppServicesException;
import is.hotelzargo.negocio.exception.EmployeeAppServicesException;
import is.hotelzargo.negocio.exception.RoomAppServicesException;
import is.hotelzargo.negocio.exception.ServicesAppServicesException;
import is.hotelzargo.negocio.exception.ShiftAppServicesException;
import is.hotelzargo.negocio.transfer.BookTransfer;
import is.hotelzargo.negocio.transfer.ClientTransfer;
import is.hotelzargo.negocio.transfer.EmployeeTransfer;
import is.hotelzargo.negocio.transfer.RoomTransfer;
import is.hotelzargo.negocio.transfer.ServiceTransfer;
import is.hotelzargo.negocio.transfer.ShiftTransfer;

public class FacadeImp implements Facade {

	@Override
	public void addClient(ClientTransfer t)throws ClientAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		ClientAppServices clientAS = fac.getClientAS();
		clientAS.addClient(t);
	}

	@Override
	public void delClient(String id) throws ClientAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		ClientAppServices clientAS = fac.getClientAS();
		clientAS.deleteClient(id);
	}

	@Override
	public void listClient() throws ClientAppServicesException {

		BusinessFactory fac = BusinessFactory.getInstance();
		ClientAppServices clientAS = fac.getClientAS();
		clientAS.listClient();
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
	public void delBook(String id) throws BookAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		BookAppServices bookAS = fac.getBookAS();
		bookAS.delBook(id);
	}

	@Override
	public void listBook() throws BookAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		BookAppServices bookAS = fac.getBookAS();
		bookAS.listBook();
	}

	@Override
	public void modBook(BookTransfer t) throws BookAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		BookAppServices bookAS = fac.getBookAS();
		bookAS.modBook(t);
	}

	@Override
	public void findBook(String id) throws BookAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		BookAppServices bookAS = fac.getBookAS();
		bookAS.findBook(id);
	}

	@Override
	public void confirmBook(String id) throws BookAppServicesException {
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
	public void delEmployee(String id) throws EmployeeAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		EmployeeAppServices employeeAS = fac.getEmployeeAS();
		employeeAS.delEmployee(id);
	}

	@Override
	public void listEmployee() throws EmployeeAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		EmployeeAppServices employeeAS = fac.getEmployeeAS();
		employeeAS.listEmployee();
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
	public void delRoom(String id) throws RoomAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		RoomAppServices roomAS = fac.getRoomAS();
		roomAS.delRoom(id);
	}

	@Override
	public void listRoom() throws RoomAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		RoomAppServices roomAS = fac.getRoomAS();
		roomAS.listRoom();
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
	public void delService(String id) throws ServicesAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		ServicesAppServices servicesAS = fac.getServicesAS();
		servicesAS.delService(id);
	}

	@Override
	public void listService() throws ServicesAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		ServicesAppServices servicesAS = fac.getServicesAS();
		servicesAS.listService();
	}

	@Override
	public void modService(ServiceTransfer t) throws ServicesAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		ServicesAppServices servicesAS = fac.getServicesAS();
		servicesAS.modService(t);
	}

	@Override
	public void addShift(ShiftTransfer t) throws ShiftAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		ShiftAppServices shiftAS = fac.getShiftAS();
		shiftAS.addShift(t);
	}

	@Override
	public void delShift(String id) throws ShiftAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		ShiftAppServices shiftAS = fac.getShiftAS();
		shiftAS.delShift(id);
	}

	@Override
	public void listShift() throws ShiftAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		ShiftAppServices shiftAS = fac.getShiftAS();
		shiftAS.listShift();
	}

	@Override
	public void modShift(ShiftTransfer t) throws ShiftAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		ShiftAppServices shiftAS = fac.getShiftAS();
		shiftAS.modShift(t);
	}

}
