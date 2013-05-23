package is.hotelzargo.integracion.client.dao;

import is.hotelzargo.integracion.exception.ClientIntegrationException;
import is.hotelzargo.negocio.client.transfer.ClientTransfer;

import java.util.Vector;

public interface ClientDAO {

	public void createClientIndividual(ClientTransfer t) throws ClientIntegrationException;
	
	public void createClientCompany(ClientTransfer t) throws ClientIntegrationException;
	
	public void deleteClient(int id) throws ClientIntegrationException;
	
	public void updateClientIndividual(ClientTransfer t) throws ClientIntegrationException;
	
	public void updateClientCompany(ClientTransfer t) throws ClientIntegrationException;
	
	public ClientTransfer getClient(int id) throws ClientIntegrationException;
	
	public boolean searchClient(int id) throws ClientIntegrationException;
	
	public Vector<ClientTransfer> listClient() throws ClientIntegrationException;

	public boolean allBooksConfirmed(int id) throws ClientIntegrationException;
	

	
}
