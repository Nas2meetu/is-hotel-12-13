package is.hotelzargo.integracion.dao;

import java.util.Vector;

import is.hotelzargo.integracion.exception.ClientIntegrationException;
import is.hotelzargo.negocio.transfer.ClientTransfer;

public interface ClientDAO {

	public void createClient(ClientTransfer t) throws ClientIntegrationException;
	
	public void deleteClient(int id) throws ClientIntegrationException;
	
	public void updateClient(ClientTransfer t) throws ClientIntegrationException;
	
	public ClientTransfer getClient(int id) throws ClientIntegrationException;
	
	public boolean searchIndividual(String dni) throws ClientIntegrationException;
	
	public boolean searchCompany(String cif) throws ClientIntegrationException;
	
	public Vector<ClientTransfer> listClient() throws ClientIntegrationException;
}
