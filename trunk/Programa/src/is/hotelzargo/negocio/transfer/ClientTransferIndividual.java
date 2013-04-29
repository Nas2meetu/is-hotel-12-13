package is.hotelzargo.negocio.transfer;

public class ClientTransferIndividual extends ClientTransfer {

	private String name;
	private String surname;
	private String dni;
	private String phone;
	private String creditCard;
	private String address;
	
	public ClientTransferIndividual(String name, 
								   String surname, 
								   String dni,
								   String phone, 
								   String creditCard, 
								   String address) {
		this.name = name;
		this.surname = surname;
		this.dni = dni;
		this.phone = phone;
		this.creditCard = creditCard;
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
	
	public String getAddress(){
		return address;
	}
	
	/* Set */
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSurname(String surname){
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
	
	public void setAddress(String address){
		this.address = address;
	}
}
