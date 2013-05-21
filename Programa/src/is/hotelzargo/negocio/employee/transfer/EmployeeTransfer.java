package is.hotelzargo.negocio.employee.transfer;

import is.hotelzargo.negocio.shift.transfer.ShiftTransfer;

public class EmployeeTransfer {

	private int id;
	private ShiftTransfer shift;
	private float pay;
	private String name;
	private String surname;
	private String DNI;
	private String phone;
	
	public EmployeeTransfer (int id, 
							  ShiftTransfer shift, 
							  float pay,
							  String name,
							  String surname,
							  String DNI,
							  String phone){
		
		this.id = id;
		this.shift = shift;
		this.pay = pay;
		this.name = name;
		this.surname = surname;
		this.DNI = DNI;
		this.phone = phone;
		
	}
	
	public int getId(){
		return this.id;
	}
	
	public ShiftTransfer getShift(){
		return this.shift;
	}
	
	public float getPay(){
		return this.pay;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getSurname(){
		return this.surname;
	}
	
	public String getDNI(){
		return this.DNI;
	}
	
	public String getPhone(){
		return this.phone;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public void setShift(ShiftTransfer shift){
		this.shift = shift;
	}
	
	public void setPay(float pay){
		this.pay = pay;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setSurname(String surname){
		this.surname = surname;
	}
	
	public void setDNI(String DNI){
		this.DNI = DNI;
	}
	
	public void setPhone(String phone){
		this.phone = phone;
	}
}
