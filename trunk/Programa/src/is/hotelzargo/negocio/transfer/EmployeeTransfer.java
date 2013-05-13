package is.hotelzargo.negocio.transfer;

import is.hotelzargo.negocio.appservices.ShiftAppServices;

public class EmployeeTransfer {

	private int id;
	private ShiftAppServices shift;
	private float pay;
	private String name;
	private String surname;
	private String DNI;
	private int tlf;
	
	public EmployeeTransfer (int id, 
							  ShiftAppServices shift, 
							  float pay,
							  String name,
							  String surname,
							  String DNI,
							  int tlf){
		
		this.id = id;
		this.shift = shift;
		this.pay = pay;
		this.name = name;
		this.surname = surname;
		this.DNI = DNI;
		this.tlf = tlf;
		
	}
	
	public int getId(){
		return this.id;
	}
	
	public ShiftAppServices getShift(){
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
	
	public int getTlf(){
		return this.tlf;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public void setShift(ShiftAppServices shift){
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
	
	public void setTlf(int tlf){
		this.tlf = tlf;
	}
}
