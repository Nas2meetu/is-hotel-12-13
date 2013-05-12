package is.hotelzargo.negocio.transfer;

public class ClientTransfer {
	int id;
	
	public ClientTransfer(int id){
		this.id = id;
	}
	
	/* Get */
	public int getID(){
		return id;
	}
	
	/* Set */
	public void setID(int id){
		this.id = id;
	}
	
}
