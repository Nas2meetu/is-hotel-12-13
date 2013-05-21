package is.hotelzargo.negocio.book.transfer;

import is.hotelzargo.negocio.service.transfer.ServiceTransfer;

import java.sql.Date;
import java.util.Vector;

public class BookTransfer {
	
	private int idBook;
	private Vector<Integer> idRoom;
	private int idClient;
	private Date checkIn;
	private Date checkOut;
	private float deposit;
	private int numPerson;
	private Vector<ServiceTransfer> services;
	
	public BookTransfer(int idBook,Vector<Integer> idRoom, int idClient,Date checkIn, Date checkOut,
						float deposit, int numPerson,Vector<ServiceTransfer> services){
			
			this.idBook = idBook;
			this.idRoom = idRoom;
			this.idClient = idClient;
			this.checkIn = checkIn;
			this.checkOut = checkOut;
			this.deposit = deposit;
			this.numPerson = numPerson;
			this.services = services;
		
	}

	public Vector<Integer> getIdRoom() {
		return idRoom;
	}

	public void setIdRoom(Vector<Integer> idRoom) {
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

	public Vector<ServiceTransfer> getServices() {
		return services;
	}

	public void setServices(Vector<ServiceTransfer> services) {
		this.services = services;
	}

	public int getIdBook() {
		return idBook;
	}

	public void setIdBook(int idBook) {
		this.idBook = idBook;
	}
	
	
	
}
