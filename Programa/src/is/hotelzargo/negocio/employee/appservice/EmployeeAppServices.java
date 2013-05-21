package is.hotelzargo.negocio.employee.appservice;

import java.util.Vector;

import is.hotelzargo.negocio.employee.transfer.EmployeeTransfer;
import is.hotelzargo.negocio.exception.EmployeeAppServicesException;

public interface EmployeeAppServices {

	void addEmployee(EmployeeTransfer t)throws EmployeeAppServicesException;

	void delEmployee(int id)throws EmployeeAppServicesException;

	Vector<EmployeeTransfer> listEmployee()throws EmployeeAppServicesException;

	void modEmployee(EmployeeTransfer t)throws EmployeeAppServicesException;

}
