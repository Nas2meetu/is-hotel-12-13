package is.hotelzargo.negocio;

import is.hotelzargo.negocio.AppServices.ClientAppServices;
import is.hotelzargo.negocio.exception.ClientAppServiceException;
import is.hotelzargo.negocio.transfer.ClientTransfer;

public class FacadeImp implements Facade {

	@Override
	public void addClient(ClientTransfer t)throws ClientAppServiceException {
		BusinessFactory fac = BusinessFactory.getInstance();
		ClientAppServices clientAS = fac.getClientAS();
		clientAS.addClient(t);
	}

}
