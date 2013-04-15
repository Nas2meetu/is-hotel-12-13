package is.hotelzargo.integracion.dao;

import is.hotelzargo.negocio.transfer.ClientTransfer;

public interface ClientDAO {

	//TODO añadir excepciones de la capa de negocio
	public void createClient(ClientTransfer t);
	
	public void deleteClient(int id);
	
	public void updateClient(ClientTransfer t);
	
	public ClientTransfer getClient(int id);
}
