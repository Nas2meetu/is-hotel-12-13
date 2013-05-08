package is.hotelzargo.negocio.appservices;

import is.hotelzargo.negocio.exception.ClientAppServicesException;
import is.hotelzargo.negocio.transfer.ClientTransfer;

public interface ClientAppServices {

	public void addClient(ClientTransfer t) throws ClientAppServicesException;
	
	public void deleteClient(String id) throws ClientAppServicesException;

	public void listClient() throws ClientAppServicesException;

	public void modClient(ClientTransfer t)throws ClientAppServicesException;
}
