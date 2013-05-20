package is.hotelzargo.negocio.transfer;

import java.sql.Date;

public class BookTransfer {
	
	private int idRoom;
	private int idClient;
	private Date checkIn;
	private Date checkOut;
	private float deposit;
	private int numPerson;
	private ServiceTransfer services;
	
	public BookTransfer(int idRoom, int idClient,Date checkIn, Date checkOut,
						float deposit, int numPerson,ServiceTransfer services){
			
			this.idRoom = idRoom;
			this.idClient = idClient;
			this.checkIn = checkIn;
			this.checkOut = checkOut;
			this.deposit = deposit;
			this.numPerson = numPerson;
			this.services = services;
		
	}

	public int getIdRoom() {
		return idRoom;
	}

	public void setIdRoom(int idRoom) {
		this.idRoom = idRoom;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}

	public float getDeposit() {
		return deposit;
	}

	public void setDeposit(float deposit) {
		this.deposit = deposit;
	}

	public int getNumPerson() {
		return numPerson;
	}

	public void setNumPerson(int numPerson) {
		this.numPerson = numPerson;
	}

	public ServiceTransfer getServices() {
		return services;
	}

	public void setServices(ServiceTransfer services) {
		this.services = services;
	}
	
	
	
}
