package is.hotelzargo.negocio.transfer;

public class RoomTransfer {
	
	private int id; 
	private int numBeds;
	private int numRoom;
	private float price;


	public RoomTransfer(int id, int numBeds, int numRoom, float price) {
		this.id = id;
		this.numBeds = numBeds;
		this.numRoom = numRoom;
		this.price = price;
	}
	
	/* Get */
	
	public int getId(){
		return id;
	}
	
	public int getnumBeds(){
		return numBeds;
	}
	
	public int getnumRoom(){
		return numRoom;
	}
	
	public float getPrice(){
		return price;
	}
	
	/* Set */
	
	public void setId(int id){
		this.id = id;
	}

	public void setnumBeds(int numBeds){
		this.numBeds = numBeds;
	}
	
	public void setnumRoom(int numRoom){
		this.numRoom = numRoom;
	}
	
	public void setprice(float price){
		this.price = price;
	}
	
}
