package is.hotelzargo.negocio;

import is.hotelzargo.negocio.exception.ClientAppServiceException;
import is.hotelzargo.negocio.transfer.ClientTransfer;

public interface Facade {

	public void addClient(ClientTransfer t)throws ClientAppServiceException;

	public void delClient(String id)throws ClientAppServiceException;

	public void listClient()throws ClientAppServiceException;
}
