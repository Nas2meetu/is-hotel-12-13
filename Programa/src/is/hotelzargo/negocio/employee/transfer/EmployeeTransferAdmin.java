package is.hotelzargo.negocio.employee.transfer;

public class EmployeeTransferAdmin extends EmployeeTransfer {
	
	private String password;
	
	public EmployeeTransferAdmin(int id, 
								 int shift, 
								 float pay,
								 String name,
								 String surname,
								 String DNI,
								 String tlf,
								 String password){
	
	super(id,shift,pay,name,surname,DNI,tlf);
	this.password = password;

	}
	
	public String getPassword(){
		return this.password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
}
