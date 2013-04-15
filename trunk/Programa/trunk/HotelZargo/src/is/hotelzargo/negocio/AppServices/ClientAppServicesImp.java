package is.hotelzargo.negocio.AppServices;

import is.hotelzargo.integracion.DAOFactory;
import is.hotelzargo.integracion.dao.ClientDAO;
import is.hotelzargo.negocio.exception.ClientAppServiceException;
import is.hotelzargo.negocio.transfer.ClientTransfer;

public class ClientAppServicesImp implements ClientAppServices {

	@Override
	public void addClient(ClientTransfer t) throws ClientAppServiceException {
		checkData(t);
		//TODO Comprobar que no exista en la BBDD
		DAOFactory fac = DAOFactory.getInstance();
		ClientDAO dao = fac.getClientDAO();
		dao.createClient(t);
	}
	
	private void checkData(ClientTransfer t) throws ClientAppServiceException {
		if(t.getName().length() == 0)
			throw new ClientAppServiceException("Nombre no valido");
		if(t.getSurname().length() == 0)
			throw new ClientAppServiceException("Apellido no valido");
		if(t.getDNI().length() != 9)
			throw new ClientAppServiceException("DNI no valido");
		if(t.getPhone().length() == 0)
			throw new ClientAppServiceException("Telefono no valido");
		if(t.getCreditCard().length() == 0)
			throw new ClientAppServiceException("Tarjeta de credito no valida");
		if(t.getCompany().length() == 0)
			throw new ClientAppServiceException("Empresa no valida");
		if(t.getAddress().length() == 0)
			throw new ClientAppServiceException("Domicilio no valido");
	}

}
