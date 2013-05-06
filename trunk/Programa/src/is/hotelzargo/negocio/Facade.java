package is.hotelzargo.negocio;

import is.hotelzargo.negocio.exception.ClientAppServicesException;
import is.hotelzargo.negocio.transfer.ClientTransfer;

public interface Facade {

	public void addClient(ClientTransfer t)throws ClientAppServicesException;

	public void delClient(String id)throws ClientAppServicesException;

	public void listClient()throws ClientAppServicesException;
}
