package is.hotelzargo.negocio.appservices;

import java.util.Vector;

import is.hotelzargo.negocio.exception.EmployeeAppServicesException;
import is.hotelzargo.negocio.transfer.EmployeeTransfer;

public interface EmployeeAppServices {

	void addEmployee(EmployeeTransfer t)throws EmployeeAppServicesException;

	void delEmployee(int id)throws EmployeeAppServicesException;

	Vector<EmployeeTransfer> listEmployee()throws EmployeeAppServicesException;

	void modEmployee(EmployeeTransfer t)throws EmployeeAppServicesException;

}
