package is.hotelzargo.negocio.AppServices;

import is.hotelzargo.negocio.exception.ClientAppServiceException;
import is.hotelzargo.negocio.transfer.ClientTransfer;

public interface ClientAppServices {

	public void addClient(ClientTransfer t) throws ClientAppServiceException;
}
