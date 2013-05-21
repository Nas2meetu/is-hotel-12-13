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
				//Se busca antes de insertar en base de datos porque mysql
				if(!dao.searchClient(((ClientTransfer) t).getID())){
					dao.createClientIndividual(t);
				}else{
					throw new ClientAppServicesException("El usuario ya existe");
				}
			} catch (ClientIntegrationException e) {
				e.getMessage();
			}
		}else {
			checkDataCompany(t);
			
			try {
				if(!dao.searchClient(((ClientTransfer) t).getID())){
					dao.createClientCompany(t);
				}else{
					throw new ClientAppServicesException("El usuario company ya existe");
				}

			} catch (ClientIntegrationException e) {
				//e.printStackTrace();
				//throw new ClientAppServicesException("La compañía ya existe");
				e.getMessage();
			}
			
			
		}
 
	}
	
	private void checkDataIndividual(ClientTransfer t) throws ClientAppServicesException {
		
		String DNI = ((ClientTransferIndividual)t).getDNI();
		String TLF = ((ClientTransferIndividual)t).getPhone();
		String TJC = ((ClientTransferIndividual)t).getCreditCard();
		
		if(((ClientTransferIndividual)t).getName().length() < 3)
			throw new ClientAppServicesException("Nombre no valido");
		if(((ClientTransferIndividual)t).getSurname().length() == 0)
			throw new ClientAppServicesException("Apellido no valido");
		if(DNI.length() != 9)
			throw new ClientAppServicesException("DNI no valido");
		if ((TLF.length() != 9)||(TLF.indexOf("9") == -1)||(TLF.indexOf("6") == -1))
			throw new ClientAppServicesException("Telefono no valido");
		if((TJC.length() < 13)||(TJC.length() > 16))
			throw new ClientAppServicesException("Tarjeta de credito no valida");
		if(((ClientTransferIndividual)t).getAddress().length() == 0)
			throw new ClientAppServicesException("Domicilio no valido");
	}
	
	private boolean checkDni(String DNI) {
		
		int num_t = 0;
		char num = ' ';
		char letters [] = {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
		
		for (int i=0 ; i<=7 ; i++){
			num = DNI.charAt(i);
			num_t += num-48; 
		}
		
	    if (letters[num_t%23]!=DNI.charAt(8)) return false;
	    else return true;
	}

	private void checkDataCompany(ClientTransfer t) throws ClientAppServicesException {
		
		String TLF = ((ClientTransferCompany)t).getPhone();
		String TJC = ((ClientTransferCompany)t).getCreditCard();
		
		if(((ClientTransferCompany)t).getCompany().length() == 0)
			throw new ClientAppServicesException("Nombre no valido");
		if(((ClientTransferCompany)t).getAddress().length() == 0)
			throw new ClientAppServicesException("Domicilio no valido");
		if ((TLF.length() != 9)||(TLF.indexOf("9") == -1)||(TLF.indexOf("6") == -1))
			throw new ClientAppServicesException("Telefono no valido");
		if((TJC.length() < 13)||(TJC.length() > 16))
			throw new ClientAppServicesException("Tarjeta de credito no valida");
		if(((ClientTransferCompany)t).getCIF().length() != 9)
			throw new ClientAppServicesException("Domicilio no valido");
	}

	@Override
	public void delClient(int id) throws ClientAppServicesException {
		DAOFactory fac = DAOFactory.getInstance();
		ClientDAO dao = fac.getClientDAO();
		
		//deleteClient(id);
		try {
			if (dao.searchClient(id)){
				dao.deleteClient(id);
			}else{
				throw new ClientAppServicesException("El usuario a eliminar no existe");
			}
		} catch (ClientIntegrationException e) {
			e.getMessage();
			throw new ClientAppServicesException("Problema al eliminar cliente");
		}
		
		/*
			try {
				dao.deleteClientIndividual(id);
			} catch (ClientIntegrationException e) {
				e.printStackTrace();
				throw new ClientAppServicesException("Problema al eliminar cliente individual");
			}
		}
		else{
			try {
				dao.deleteClientCompany(id);
			} catch (ClientIntegrationException e) {
				e.printStackTrace();
				throw new ClientAppServicesException("Problema al eliminar cliente compañía");				
			}
		}
		*/
	}

	@Override
	public Vector<ClientTransfer> listClient() throws ClientAppServicesException {
		DAOFactory fac = DAOFactory.getInstance();
		ClientDAO dao = fac.getClientDAO();
		try {
			return dao.listClient();
		} catch (ClientIntegrationException e) {
			e.getMessage();
			throw new ClientAppServicesException("Problema al listar clientes");
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
				e.printStackTrace();
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
				e.printStackTrace();
			}
		}
		
	}

}
