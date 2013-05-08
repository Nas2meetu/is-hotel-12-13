package is.hotelzargo.negocio.appservices;

import is.hotelzargo.negocio.exception.EmployeeAppServicesException;
import is.hotelzargo.negocio.transfer.EmployeeTransfer;

public interface EmployeeAppServices {

	void addEmployee(EmployeeTransfer t)throws EmployeeAppServicesException;

	void delEmployee(String id)throws EmployeeAppServicesException;

	void listEmployee()throws EmployeeAppServicesException;

	void modEmployee(EmployeeTransfer t)throws EmployeeAppServicesException;

}
