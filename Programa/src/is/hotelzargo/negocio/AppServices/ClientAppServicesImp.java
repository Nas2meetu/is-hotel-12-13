package is.hotelzargo.negocio.AppServices;

import is.hotelzargo.integracion.DAOFactory;
import is.hotelzargo.integracion.dao.ClientDAO;
import is.hotelzargo.integracion.exception.ClientIntegrationException;
import is.hotelzargo.negocio.exception.ClientAppServiceException;
import is.hotelzargo.negocio.transfer.ClientTransfer;
import is.hotelzargo.negocio.transfer.ClientTransferIndividual;

public class ClientAppServicesImp implements ClientAppServices {

	@Override
	public void addClient(ClientTransfer t) throws ClientAppServiceException {
		
		DAOFactory fac = DAOFactory.getInstance();
		ClientDAO dao = fac.getClientDAO();
		
		if(t instanceof ClientTransferIndividual ) {
			
			checkDataIndividual(t);
			
			try {
				if(!dao.searchIndividual(((ClientTransferIndividual) t).getDNI())){
					dao.createClient(t);
				}else{
					throw new ClientAppServiceException("El usuario ya exite");
				}
			} catch (ClientIntegrationException e) {
				//e.printStackTrace();
				e.getMessage();
			}
		}else {
			checkDataCompany(t);
		}
		//TODO Comprobar que no exista en la BBDD
	}
	
	private void checkDataIndividual(ClientTransfer t) throws ClientAppServiceException {
		/*
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
			throw new ClientAppServiceException("Domicilio no valido");*/
	}
	
	private void checkDataCompany(ClientTransfer t) throws ClientAppServiceException {
		/*if(t.getName().length() == 0)
			throw new ClientAppServiceException("Nombre no valido");
		if(t.getSurname().length() == 0)
			throw new ClientAppServiceException("Apellido no valido");*/
		//TODO CHECK
	}

	@Override
	public void deleteClient(String id) {
		DAOFactory fac = DAOFactory.getInstance();
		ClientDAO dao = fac.getClientDAO();
		//checkInt(id);
		try {
			dao.deleteClient(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ClientIntegrationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void listClient() throws ClientAppServiceException {
		DAOFactory fac = DAOFactory.getInstance();
		ClientDAO dao = fac.getClientDAO();
		try {
			dao.listClient();
		} catch (ClientIntegrationException e) {
			e.printStackTrace();
		}
	}

}
