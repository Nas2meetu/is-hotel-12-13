package is.hotelzargo.negocio.book.transfer;

import java.util.Vector;

public class BookTransfer {
	
	private int idBook;
	private Vector<Integer> idRoom;
	private int idClient;
	private String checkIn;
	private String checkOut;
	private float deposit;
	private int numPerson;
	private Vector<Integer> services;
	private boolean confirm;
	
	public BookTransfer(int idBook,Vector<Integer> idRoom, int idClient,String checkIn, String checkOut,
						float deposit, int numPerson,Vector<Integer> services,boolean confirm){
			
			this.idBook = idBook;
			this.idRoom = idRoom;
			this.idClient = idClient;
			this.checkIn = checkIn;
			this.checkOut = checkOut;
			this.deposit = deposit;
			this.numPerson = numPerson;
			this.services = services;
			this.setConfirm(confirm);
		
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

	public String getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}

	public String getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(String checkOut) {
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

	public Vector<Integer> getServices() {
		return services;
	}

	public void setServices(Vector<Integer> services) {
		this.services = services;
	}

	public int getIdBook() {
		return idBook;
	}

	public void setIdBook(int idBook) {
		this.idBook = idBook;
	}

	public boolean isConfirm() {
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}
	
	
	
}
