package is.hotelzargo.integracion.dao;

import java.util.Vector;

import is.hotelzargo.integracion.exception.ClientIntegrationException;
import is.hotelzargo.negocio.transfer.ClientTransfer;

public class ClientDAOImp implements ClientDAO {

	//TODO Gorka: todas las llamadas a la BBDD
	@Override
	public void createClient(ClientTransfer t) throws ClientIntegrationException {
		
	}

	@Override
	public void deleteClient(int id) throws ClientIntegrationException {
		
	}

	@Override
	public void updateClient(ClientTransfer t) throws ClientIntegrationException {
		
	}

	@Override
	public ClientTransfer getClient(int id) throws ClientIntegrationException {
		return null;
	}

	@Override
	public boolean searchIndividual(String dni) throws ClientIntegrationException {
		return false;
	}

	@Override
	public boolean searchCompany(String cif) throws ClientIntegrationException {
		return false;
	}

	@Override
	public Vector<ClientTransfer> listClient() throws ClientIntegrationException {
		Vector<ClientTransfer> list = new Vector<ClientTransfer>();
		
		return list;
	}	
}
