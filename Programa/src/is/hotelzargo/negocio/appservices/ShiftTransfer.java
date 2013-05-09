package is.hotelzargo.negocio.transfer;

public class ShiftTransfer {
	//turno, horaentrada, horasalida, id
	private string turno;
	private date horaEntrada;
	private date horaSalida;
	private int id;
	
	
	public ShiftTransfer(string turno,
						date horaEntrada,
						date horaSalida,
						int id){
		//TODO transfer turnos
		this.turno = turno;
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;
		this.id;
	}
	
	public String getTurno () {
		return turno;
	}
	
	public date getHoraEntrada () {
		return horaEntrada;
	}
	
	public date getHoraSalida () {
		return horaSalida;
	}
	
	public int getId () {
		return id;
	}
	
	
	public void setTurno (string turno) {
		this.turno = turno;
	}
	
	public void setHoraEntrada (date horaEntrada) {
		this.horaEntrada = horaEntrada;
	}
	
	public void setHoraSalida (date horaSalida) {
		this.horaSalida = horaSalida;
	}
	
	public void setId (int id) {
		this-id = id;
	}
}
