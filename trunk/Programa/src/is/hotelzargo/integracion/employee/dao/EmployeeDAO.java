package is.hotelzargo.integracion.employee.dao;

import java.util.Vector;

import is.hotelzargo.integracion.exception.EmployeeIntegrationException;
import is.hotelzargo.negocio.employee.transfer.EmployeeTransfer;

public interface EmployeeDAO {
	
	public void createEmployeeAdmin(EmployeeTransfer t) throws EmployeeIntegrationException;
	
	public void createEmployeeServices(EmployeeTransfer t) throws EmployeeIntegrationException;
	
	public void deleteEmployee(int id) throws EmployeeIntegrationException;
	
	public EmployeeTransfer getEmployee(int id) throws EmployeeIntegrationException;
	
	public Vector<EmployeeTransfer> listEmployee() throws EmployeeIntegrationException;
	
	public void updateEmployeeAdmin(EmployeeTransfer t) throws EmployeeIntegrationException;
	
	public void updateEmployeeServices(EmployeeTransfer t) throws EmployeeIntegrationException;

	public boolean searchEmployee(String dni) throws EmployeeIntegrationException;
	
	public boolean searchEmployeeByID(int id) throws EmployeeIntegrationException;

	public boolean existsShift(int shift) throws EmployeeIntegrationException;


}
