package is.hotelzargo.integracion.dao;

import is.hotelzargo.integracion.exception.EmployeeIntegrationException;
import is.hotelzargo.negocio.transfer.EmployeeTransfer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class EmployeeDAOImp implements EmployeeDAO {
	
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet rs = null;
    
    
    public EmployeeDAOImp(Connection connection){
    	this.connection = connection;
    	try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
    }

	@Override
	public void createEmployee(EmployeeTransfer t)
			throws EmployeeIntegrationException {
		// TODO llamadas a la BBDD
		
	}

	@Override
	public void deleteEmployee(String id) throws EmployeeIntegrationException {
		// TODO llamadas a la BBDD
		
	}

	@Override
	public EmployeeTransfer getEmployee(String id)
			throws EmployeeIntegrationException {
		// TODO llamadas a la BBDD
		return null;
	}

	@Override
	public Vector<EmployeeTransfer> listEmployee()
			throws EmployeeIntegrationException {
		// TODO llamadas a la BBDD
		return null;
	}

	@Override
	public void updateEmployee(EmployeeTransfer t) throws EmployeeIntegrationException {
		// TODO llamadas a la BBDD
		
	}

	@Override
	public boolean searchEmployee(String dni)
			throws EmployeeIntegrationException {
		// TODO llamadas a la BBDD
		return false;
	}

}
