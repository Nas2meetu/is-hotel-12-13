package is.hotelzargo.negocio.client.appservice;

import java.util.Vector;

import is.hotelzargo.negocio.client.transfer.ClientTransfer;
import is.hotelzargo.negocio.exception.ClientAppServicesException;

public interface ClientAppServices {

	public void addClient(ClientTransfer t) throws ClientAppServicesException;
	
	public void delClient(int id) throws ClientAppServicesException;

	public Vector<ClientTransfer> listClient() throws ClientAppServicesException;

	public void modClient(ClientTransfer t)throws ClientAppServicesException;
}
