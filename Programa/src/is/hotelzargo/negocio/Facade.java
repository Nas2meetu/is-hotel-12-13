package is.hotelzargo.negocio;

import java.util.Vector;

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

public interface Facade {

	public void addClient(ClientTransfer t)throws ClientAppServicesException;

	public void delClient(int id)throws ClientAppServicesException;

	public Vector<ClientTransfer> listClient()throws ClientAppServicesException;
	
	public void modClient(ClientTransfer t)throws ClientAppServicesException;
	
	public void addBook(BookTransfer t)throws BookAppServicesException;
	
	public void delBook(int id)throws BookAppServicesException;
	
	public void listBook()throws BookAppServicesException;
	
	public void modBook(BookTransfer t)throws BookAppServicesException;
	
	public void findBook(int id)throws BookAppServicesException;
	
	public void confirmBook(int id)throws BookAppServicesException;
	
	public void addEmployee(EmployeeTransfer t)throws EmployeeAppServicesException;
	
	public void delEmployee(int id)throws EmployeeAppServicesException;
	
	public void listEmployee()throws EmployeeAppServicesException;
	
	public void modEmployee(EmployeeTransfer t)throws EmployeeAppServicesException;
	
	public void addRoom(RoomTransfer t)throws RoomAppServicesException;
	
	public void delRoom(int id)throws RoomAppServicesException;
	
	public void listRoom()throws RoomAppServicesException;
	
	public void modRoom(RoomTransfer t)throws RoomAppServicesException;
	
	public void addService(ServiceTransfer t)throws ServicesAppServicesException;
	
	public void delService(int id)throws ServicesAppServicesException;
	
	public void listService()throws ServicesAppServicesException;
	
	public void modService(ServiceTransfer t)throws ServicesAppServicesException;
	
	public void addShift(ShiftTransfer t)throws ShiftAppServicesException;
	
	public void delShift(int id)throws ShiftAppServicesException;
	
	public void listShift()throws ShiftAppServicesException;
	
	public void modShift(ShiftTransfer t)throws ShiftAppServicesException;
}
