package is.hotelzargo.negocio.transfer;

import is.hotelzargo.negocio.appservices.ShiftAppServices;

public class EmployeeTransferAdmin extends EmployeeTransfer {
	
	private String password;
	
	public EmployeeTransferAdmin(int id, 
								 ShiftAppServices shift, 
								 float pay,
								 String name,
								 String surname,
								 String DNI,
								 int tlf,
								 String password){
	
	super(id,shift,pay,name,surname,DNI,tlf);
	this.password = password;

	}
	
	public String getPassword(){
		return this.password;
	}
	
	public void getPassword(String password){
		this.password = password;
	}
	
}
