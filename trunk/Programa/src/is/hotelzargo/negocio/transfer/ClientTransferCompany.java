package is.hotelzargo.negocio.transfer;

public class ClientTransferCompany extends ClientTransfer {

	private String company;
	private String cif;
	private String phone;
	private String creditCard;
	private String address;
	
	public ClientTransferCompany(String company,
								 String cif,
								 String phone,
								 String creditCard,
								 String address) {
		this.company = company;
		this.cif = cif;
		this.phone = phone;
		this.creditCard = creditCard;
		this.address = address;
	}
	
	/* Get */
	
	public String getCompany(){
		return company;
	}
	
	public String getCIF(){
		return cif;
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
	
	public void setCompany(String company){
		this.company = company;
	}

	public void setCIF(String cif){
		this.cif = cif;
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
