package is.hotelzargo.negocio.employee.transfer;

public class EmployeeTransfer {

	private int id;
	private int shift;
	private float pay;
	private String name;
	private String surname;
	private String DNI;
	private String phone;
	
	public EmployeeTransfer (int id, 
							  int shift, 
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
	
	public int getShift(){
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
	
	public void setShift(int shift){
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
