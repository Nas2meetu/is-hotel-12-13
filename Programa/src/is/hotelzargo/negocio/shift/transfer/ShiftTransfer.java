package is.hotelzargo.negocio.shift.transfer;

import java.sql.Time;



public class ShiftTransfer {
	
	//TODO transfer turnos traducir
	
	//turno, horaentrada, horasalida
	private int id;
	private String shift;
	private Time checkin;
	private Time chekout;
	
	
	public ShiftTransfer(int id,
						String shift,
						Time checkin,
						Time chekout){ 
		this.id = id;
		this.shift = shift;
		this.checkin = checkin;
		this.chekout = chekout;
	}
	
	public String getShift () {
		return shift;
	}
	
	public Time getCheckin () {
		return checkin;
	}
	
	public Time getCheckout () {
		return chekout;
	}
		
	public void setShift (String shift) {
		this.shift = shift;
	}
	
	public void setCheckin (Time checkin) {
		this.checkin = checkin;
	}
	
	public void setCheckout (Time chekout) {
		this.chekout = chekout;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
