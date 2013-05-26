package is.hotelzargo.negocio.shift.transfer;

public class ShiftTransfer {
	
	private int id;
	private String shift;
	private String checkin;
	private String chekout;
	
	
	public ShiftTransfer(int id,
						String shift,
						String checkin,
						String chekout){ 
		this.id = id;
		this.shift = shift;
		this.checkin = checkin;
		this.chekout = chekout;
	}
	
	public String getShift () {
		return shift;
	}
	
	public String getCheckin () {
		return checkin;
	}
	
	public String getCheckout () {
		return chekout;
	}
		
	public void setShift (String shift) {
		this.shift = shift;
	}
	
	public void setCheckin (String checkin) {
		this.checkin = checkin;
	}
	
	public void setCheckout (String chekout) {
		this.chekout = chekout;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
