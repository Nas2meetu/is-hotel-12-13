package is.hotelzargo.integracion.factory;

import is.hotelzargo.integracion.book.dao.BookDAO;
import is.hotelzargo.integracion.client.dao.ClientDAO;
import is.hotelzargo.integracion.employee.dao.EmployeeDAO;
import is.hotelzargo.integracion.room.dao.RoomDAO;
import is.hotelzargo.integracion.service.dao.ServicesDAO;
import is.hotelzargo.integracion.shift.dao.ShiftDAO;

public abstract class DAOFactory {

	private static DAOFactory instance = null;
	
	public static DAOFactory getInstance(){
		if(instance == null){
			instance = new DAOFactoryImp();
		}
		return (DAOFactoryImp) instance;
	}
	
	public abstract ClientDAO getClientDAO();
	public abstract BookDAO getBookDAO();
	public abstract EmployeeDAO getEmployeeDAO();
	public abstract RoomDAO getRoomDAO();
	public abstract ServicesDAO getServicesDAO();
	public abstract ShiftDAO getShiftDAO();
}
