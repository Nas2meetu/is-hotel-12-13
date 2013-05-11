package is.hotelzargo.negocio.appservices;

import java.sql.Date;

public class ShiftTransfer {
	//turno, horaentrada, horasalida, id
	private String turno;
	private Date horaEntrada;
	private Date horaSalida;
	private int id;
	
	
	public ShiftTransfer(String turno,
						Date horaEntrada,
						Date horaSalida,
						int id){
		//TODO transfer turnos
		this.turno = turno;
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;
		this.id = id;
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
	
	public int getId () {
		return id;
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
	
	public void setId (int id) {
		this.id = id;
	}
}
