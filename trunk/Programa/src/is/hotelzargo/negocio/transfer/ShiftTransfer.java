package is.hotelzargo.negocio.transfer;

import java.sql.Date;



public class ShiftTransfer {
	
	//TODO transfer turnos traducir
	
	//turno, horaentrada, horasalida
	private int id;
	private String turno;
	private Date horaEntrada;
	private Date horaSalida;
	
	
	public ShiftTransfer(int id,
						String turno,
						Date horaEntrada,
						Date horaSalida){ 
		this.setId(id);
		this.turno = turno;
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;
	}
	
	public String getTurno () {
		return turno;
	}
	
	public Date getHoraEntrada () {
		return horaEntrada;
	}
	
	public Date getHoraSalida () {
		return horaSalida;
	}
		
	public void setTurno (String turno) {
		this.turno = turno;
	}
	
	public void setHoraEntrada (Date horaEntrada) {
		this.horaEntrada = horaEntrada;
	}
	
	public void setHoraSalida (Date horaSalida) {
		this.horaSalida = horaSalida;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
