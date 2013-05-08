package is.hotelzargo.integracion;

import is.hotelzargo.integracion.dao.BookDAO;
import is.hotelzargo.integracion.dao.ClientDAO;
import is.hotelzargo.integracion.dao.EmployeeDAO;
import is.hotelzargo.integracion.dao.RoomDAO;
import is.hotelzargo.integracion.dao.ServicesDAO;
import is.hotelzargo.integracion.dao.ShiftDAO;

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
