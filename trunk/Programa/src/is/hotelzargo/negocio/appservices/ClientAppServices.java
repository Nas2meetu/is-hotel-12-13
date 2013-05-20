package is.hotelzargo.negocio.appservices;

import java.util.Vector;

import is.hotelzargo.negocio.exception.ClientAppServicesException;
import is.hotelzargo.negocio.transfer.ClientTransfer;

public interface ClientAppServices {

	public void addClient(ClientTransfer t) throws ClientAppServicesException;
	
	public void delClient(int id) throws ClientAppServicesException;

	public Vector<ClientTransfer> listClient() throws ClientAppServicesException;

	public void modClient(ClientTransfer t)throws ClientAppServicesException;
}
