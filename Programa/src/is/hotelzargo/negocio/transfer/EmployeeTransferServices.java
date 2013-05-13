package is.hotelzargo.negocio.transfer;

import is.hotelzargo.negocio.appservices.ShiftAppServices;

public class EmployeeTransferServices extends EmployeeTransfer {
	
	public EmployeeTransferServices(int id, 
			  						ShiftAppServices shift, 
			  						float pay,
			  						String name,
			  						String surname,
			  						String DNI,
			  						int tlf){
		
		super(id,shift,pay,name,surname,DNI,tlf);
		
	}

}