package is.hotelzargo.negocio.transfer;

public class EmployeeTransferServices extends EmployeeTransfer {
	
	public EmployeeTransferServices(int id, 
			  						ShiftTransfer shift, 
			  						float pay,
			  						String name,
			  						String surname,
			  						String DNI,
			  						String tlf){
		
		super(id,shift,pay,name,surname,DNI,tlf);
		
	}

}