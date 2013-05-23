package is.hotelzargo.negocio.employee.transfer;

import is.hotelzargo.negocio.shift.transfer.ShiftTransfer;

public class EmployeeTransferServices extends EmployeeTransfer {
	
	public EmployeeTransferServices(int id, 
			  						int shift, 
			  						float pay,
			  						String name,
			  						String surname,
			  						String DNI,
			  						String tlf){
		
		super(id,shift,pay,name,surname,DNI,tlf);
		
	}

}