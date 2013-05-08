package is.hotelzargo.negocio.appservices;

import is.hotelzargo.integracion.DAOFactory;
import is.hotelzargo.integracion.dao.EmployeeDAO;
import is.hotelzargo.integracion.exception.EmployeeIntegrationException;
import is.hotelzargo.negocio.exception.EmployeeAppServicesException;
import is.hotelzargo.negocio.transfer.EmployeeTransfer;

public class EmployeeAppServicesImp implements EmployeeAppServices {

	@Override
	public void addEmployee(EmployeeTransfer t)
			throws EmployeeAppServicesException {
		// TODO crear empleado ojo con los tipos
		DAOFactory fac = DAOFactory.getInstance();
		EmployeeDAO dao = fac.getEmployeeDAO();
		
		try {
			dao.createEmployee(t);
		} catch (EmployeeIntegrationException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delEmployee(String id) throws EmployeeAppServicesException {
		// TODO borrar empleado
		DAOFactory fac = DAOFactory.getInstance();
		EmployeeDAO dao = fac.getEmployeeDAO();
		
		try {
			dao.deleteEmployee(id);
		} catch (EmployeeIntegrationException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void listEmployee() throws EmployeeAppServicesException {
		// TODO listar empleados
		DAOFactory fac = DAOFactory.getInstance();
		EmployeeDAO dao = fac.getEmployeeDAO();
		
		try {
			dao.listEmployee();
		} catch (EmployeeIntegrationException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void modEmployee(EmployeeTransfer t)
			throws EmployeeAppServicesException {
		// TODO modificar empleado
		DAOFactory fac = DAOFactory.getInstance();
		EmployeeDAO dao = fac.getEmployeeDAO();
		
		try {
			dao.updateEmployee(t);
		} catch (EmployeeIntegrationException e) {
			e.printStackTrace();
		}
		
	}

}
