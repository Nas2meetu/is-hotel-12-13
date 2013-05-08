package is.hotelzargo.negocio.appservices;

import is.hotelzargo.integracion.DAOFactory;
import is.hotelzargo.integracion.dao.ClientDAO;
import is.hotelzargo.integracion.exception.ClientIntegrationException;
import is.hotelzargo.negocio.exception.ClientAppServicesException;
import is.hotelzargo.negocio.transfer.ClientTransfer;
import is.hotelzargo.negocio.transfer.ClientTransferCompany;
import is.hotelzargo.negocio.transfer.ClientTransferIndividual;

public class ClientAppServicesImp implements ClientAppServices {

	@Override
	public void addClient(ClientTransfer t) throws ClientAppServicesException {
		
		DAOFactory fac = DAOFactory.getInstance();
		ClientDAO dao = fac.getClientDAO();
		
		if(t instanceof ClientTransferIndividual ) {
			
			checkDataIndividual(t);
			
			try {
				if(!dao.searchIndividual(((ClientTransferIndividual) t).getDNI())){
					dao.createClient(t);
				}else{
					throw new ClientAppServicesException("El usuario ya exite");
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
		if(!checkDni(DNI))
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
	public void listClient() throws ClientAppServicesException {
		DAOFactory fac = DAOFactory.getInstance();
		ClientDAO dao = fac.getClientDAO();
		try {
			dao.listClient();
		} catch (ClientIntegrationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void modClient(ClientTransfer t) throws ClientAppServicesException {
		// TODO modificar reserva
		
	}

}
