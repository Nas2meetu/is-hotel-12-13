package is.hotelzargo.negocio.transfer;

import java.sql.Date;



public class ShiftTransfer {
	
	//TODO transfer turnos traducir
	
	//turno, horaentrada, horasalida
	private int id;
	private String shift;
	private Date checkin;
	private Date chekout;
	
	
	public ShiftTransfer(int id,
						String shift,
						Date checkin,
						Date chekout){ 
		this.id = id;
		this.shift = shift;
		this.checkin = checkin;
		this.chekout = chekout;
	}
	
	public String getShift () {
		return shift;
	}
	
	public Date getCheckin () {
		return checkin;
	}
	
	public Date getCheckout () {
		return chekout;
	}
		
	public void setShift (String shift) {
		this.shift = shift;
	}
	
	public void setCheckin (Date checkin) {
		this.checkin = checkin;
	}
	
	public void setCheckout (Date chekout) {
		this.chekout = chekout;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
