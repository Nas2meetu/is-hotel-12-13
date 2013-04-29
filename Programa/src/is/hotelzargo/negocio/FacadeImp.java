package is.hotelzargo.negocio;

import is.hotelzargo.negocio.appservices.ClientAppServices;
import is.hotelzargo.negocio.exception.ClientAppServicesException;
import is.hotelzargo.negocio.transfer.ClientTransfer;

public class FacadeImp implements Facade {

	@Override
	public void addClient(ClientTransfer t)throws ClientAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		ClientAppServices clientAS = fac.getClientAS();
		clientAS.addClient(t);
	}

	@Override
	public void delClient(String id) throws ClientAppServicesException {
		BusinessFactory fac = BusinessFactory.getInstance();
		ClientAppServices clientAS = fac.getClientAS();
		clientAS.deleteClient(id);
	}

	@Override
	public void listClient() throws ClientAppServicesException {

		BusinessFactory fac = BusinessFactory.getInstance();
		ClientAppServices clientAS = fac.getClientAS();
		clientAS.listClient();
	}

}
