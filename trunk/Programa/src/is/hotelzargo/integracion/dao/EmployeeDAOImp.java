package is.hotelzargo.integracion.dao;

import is.hotelzargo.integracion.exception.EmployeeIntegrationException;
import is.hotelzargo.negocio.transfer.EmployeeTransfer;

import java.util.Vector;

public class EmployeeDAOImp implements EmployeeDAO {

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

}
