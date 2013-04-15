package is.hotelzargo.negocio.transfer;

public class ClientTransfer {
	
	private String name;
	private String surname;
	private String dni;
	private String phone;
	private String creditCard;
	private String company;
	private String address;
	
	public ClientTransfer(String name, 
						  String surname, 
						  String dni, 
						  String phone, 
						  String creditCard, 
						  String company, 
						  String address) {
		this.name = name;
		this.surname = surname;
		this.dni = dni;
		this.phone = phone;
		this.creditCard = creditCard;
		this.company = company;
		this.address = address;
	}
	
	/* Get */
	public String getName(){
		return name;
	}
	
	public String getSurname(){
		return surname;
	}
	
	public String getDNI(){
		return dni;
	}
	
	public String getPhone(){
		return phone;
	}
	
	public String getCreditCard(){
		return creditCard;
	}
	
	public String getCompany(){
		return company;
	}
	
	public String getAddress(){
		return address;
	}
	/* Set */
	
	public void setsurname(String surname){
		this.surname = surname;
	}
	
	public void setDNI(String dni){
		this.dni = dni;
	}
	
	public void setPhone(String phone){
		this.phone = phone;
	}
	
	public void setCreditCard(String creditCard){
		this.creditCard = creditCard;
	}
	
	public void setCompany(String company){
		this.company = company;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
}
