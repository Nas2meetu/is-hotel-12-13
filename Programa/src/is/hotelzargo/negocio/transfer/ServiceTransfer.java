package is.hotelzargo.negocio.transfer;

public class ServiceTransfer {
	
	private int iD;
	private String services;
	
	public ServiceTransfer(int id,String services){
		iD = id;
		this.services = services;
	}
	
	/*
	 * getters
	 */
	public int getId(){
		return this.iD;
	}
	
	public String getServices(){
		return this.services;
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
