package is.hotelzargo.negocio.transfer;

public class ServiceTransfer {
	
	private int iD;
	private String services;
	
	/*
	 * getters
	 */
	public int getId(){
		return this.iD;
	}
	
	public String getServices(){
		return this.services;
	}
	
	/*
	 *  setters
	 */
	public void setId(int iD){
		
		this.iD = iD;
	}
	
	public void setServices(String services){
		this.services = services;
	}
	
	/*
	 * Add new Services in a Service that already exists
	 */
	public void addServices(String services){
		this.services += services;	
	}
	

}
