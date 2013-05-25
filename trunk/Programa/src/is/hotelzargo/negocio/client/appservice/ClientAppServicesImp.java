package is.hotelzargo.negocio.client.appservice;

import java.util.Vector;

import is.hotelzargo.integracion.client.dao.ClientDAO;
import is.hotelzargo.integracion.exception.ClientIntegrationException;
import is.hotelzargo.integracion.factory.DAOFactory;
import is.hotelzargo.negocio.client.transfer.ClientTransfer;
import is.hotelzargo.negocio.client.transfer.ClientTransferCompany;
import is.hotelzargo.negocio.client.transfer.ClientTransferIndividual;
import is.hotelzargo.negocio.exception.ClientAppServicesException;

public class ClientAppServicesImp implements ClientAppServices {

	@Override
	public void addClient(ClientTransfer t) throws ClientAppServicesException {
		
		DAOFactory fac = DAOFactory.getInstance();
		ClientDAO dao = fac.getClientDAO();
		
		if(t instanceof ClientTransferIndividual ) {
			
			checkDataIndividual(t);
			
			try {
				if(!dao.searchClientByDni(((ClientTransferIndividual) t).getDNI())){
					dao.createClientIndividual(t);
				}else{
					throw new ClientAppServicesException("El usuario ya existe");
				}
			} catch (ClientIntegrationException e) {
				throw new ClientAppServicesException(e.getMessage());
			}
		}else {
			checkDataCompany(t);
			
			try {
				if(!dao.searchClientByCif(((ClientTransferCompany) t).getCIF())){
					dao.createClientCompany(t);
				}else{
					throw new ClientAppServicesException("El usuario company ya existe");
				}

			} catch (ClientIntegrationException e) {
				throw new ClientAppServicesException(e.getMessage());
			}
			
			
		}
 
	}

	@Override
	public void delClient(int id) throws ClientAppServicesException {
		DAOFactory fac = DAOFactory.getInstance();
		ClientDAO dao = fac.getClientDAO();
		
		try {
			if (dao.searchClient(id)){
				if(dao.allBooksConfirmed(id)){
					dao.deleteClient(id);
				}
				else{
					throw new ClientAppServicesException("El usuario a eliminar tiene reservas pendientes de confirmar");
				}
			}else{
				throw new ClientAppServicesException("El usuario a eliminar no existe");
			}
		} catch (ClientIntegrationException e) {
			throw new ClientAppServicesException(e.getMessage());
		}
		
	}

	@Override
	public Vector<ClientTransfer> listClient() throws ClientAppServicesException {
		DAOFactory fac = DAOFactory.getInstance();
		ClientDAO dao = fac.getClientDAO();
		try {
			return dao.listClient();
		} catch (ClientIntegrationException e) {
			throw new ClientAppServicesException(e.getMessage());
		}

	}

	@Override
	public void modClient(ClientTransfer t) throws ClientAppServicesException {

		DAOFactory fac = DAOFactory.getInstance();
		ClientDAO dao = fac.getClientDAO();
		
		if(t instanceof ClientTransferIndividual ) {
			try {
				if(dao.searchClient(t.getID())){
					dao.updateClientIndividual(t);
				}else{
					throw new ClientAppServicesException("Cliente no encontrado");
				}
			} catch (ClientIntegrationException e) {
				throw new ClientAppServicesException(e.getMessage());
			}
		}
		else{
			try {
				if(dao.searchClient(t.getID())){
					dao.updateClientCompany(t);
				}else{
					throw new ClientAppServicesException("Cliente no encontrado");
				}
			} catch (ClientIntegrationException e) {
				throw new ClientAppServicesException(e.getMessage());
			}
		}
		
	}
	
	private void checkDataIndividual(ClientTransfer t) throws ClientAppServicesException {
		
		String dni = ((ClientTransferIndividual)t).getDNI();
		String phone = ((ClientTransferIndividual)t).getPhone();
		String creditCard = ((ClientTransferIndividual)t).getCreditCard();
		
		if(((ClientTransferIndividual)t).getName().length() < 3)
			throw new ClientAppServicesException("Nombre no valido");
		if(((ClientTransferIndividual)t).getSurname().length() == 0)
			throw new ClientAppServicesException("Apellido no valido");
		if(dni.length() != 9)
			throw new ClientAppServicesException("DNI no valido");
		if ((phone.length() != 9)||(phone.indexOf("9") == -1)||(phone.indexOf("6") == -1))
			throw new ClientAppServicesException("Telefono no valido");
		if((creditCard.length() < 13)||(creditCard.length() > 16))
			throw new ClientAppServicesException("Tarjeta de credito no valida");
		if(((ClientTransferIndividual)t).getAddress().length() == 0)
			throw new ClientAppServicesException("Domicilio no valido");
	}

	private void checkDataCompany(ClientTransfer t) throws ClientAppServicesException {
		
		String phone = ((ClientTransferCompany)t).getPhone();
		String creditCard = ((ClientTransferCompany)t).getCreditCard();
		
		if(((ClientTransferCompany)t).getCompany().length() == 0)
			throw new ClientAppServicesException("Nombre no valido");
		if(((ClientTransferCompany)t).getAddress().length() == 0)
			throw new ClientAppServicesException("Domicilio no valido");
		if ((phone.length() != 9)||(phone.indexOf("9") == -1)||(phone.indexOf("6") == -1))
			throw new ClientAppServicesException("Telefono no valido");
		if((creditCard.length() < 13)||(creditCard.length() > 16))
			throw new ClientAppServicesException("Tarjeta de credito no valida");
		if(((ClientTransferCompany)t).getCIF().length() != 9)
			throw new ClientAppServicesException("Domicilio no valido");
	}

}
