package is.hotelzargo.integracion.factory;

import is.hotelzargo.integracion.book.dao.BookDAO;
import is.hotelzargo.integracion.book.dao.BookDAOImp;
import is.hotelzargo.integracion.client.dao.ClientDAO;
import is.hotelzargo.integracion.client.dao.ClientDAOImp;

import is.hotelzargo.integracion.employee.dao.EmployeeDAO;
import is.hotelzargo.integracion.employee.dao.EmployeeDAOImp;
import is.hotelzargo.integracion.room.dao.RoomDAO;
import is.hotelzargo.integracion.room.dao.RoomDAOImp;
import is.hotelzargo.integracion.service.dao.ServicesDAO;
import is.hotelzargo.integracion.service.dao.ServicesDAOImp;
import is.hotelzargo.integracion.shift.dao.ShiftDAO;
import is.hotelzargo.integracion.shift.dao.ShiftDAOImp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DAOFactoryImp extends DAOFactory {
	
	//TODO todas las funciones que hagas aqui menos los get de los DAO son privadas
    private static Connection connection = null;
    private Statement s = null;
    private ResultSet rs = null;	
	

	public DAOFactoryImp() {}
	
	@Override
	public ClientDAO getClientDAO() {
		return new ClientDAOImp();
	}

	@Override
	public BookDAO getBookDAO() {
		return new BookDAOImp();
	}

	@Override
	public EmployeeDAO getEmployeeDAO() {
		return new EmployeeDAOImp();
	}

	@Override
	public RoomDAO getRoomDAO() {
		return new RoomDAOImp();
	}

	@Override
	public ServicesDAO getServicesDAO() {
		return new ServicesDAOImp();
	}

	@Override
	public ShiftDAO getShiftDAO() {
		return new ShiftDAOImp();
	}
	
}
