package is.hotelzargo.integracion.dao;

import is.hotelzargo.integracion.exception.ClientIntegrationException;
import is.hotelzargo.negocio.transfer.ClientTransfer;

import java.util.Vector;

public interface ClientDAO {

	public void createClientIndividual(ClientTransfer t) throws ClientIntegrationException;
	
	public void createClientCompany(ClientTransfer t) throws ClientIntegrationException;
	
	public void deleteClientIndividual(String id) throws ClientIntegrationException;
	
	public void deleteClientCompany(String id) throws ClientIntegrationException;
	
	public void updateClientIndividual(ClientTransfer t) throws ClientIntegrationException;
	
	public void updateClientCompany(ClientTransfer t) throws ClientIntegrationException;
	
	public ClientTransfer getClientIndividual(int id) throws ClientIntegrationException;
	
	public ClientTransfer getClientIndividualByDni(String dni) throws ClientIntegrationException;
	
	public ClientTransfer getClientCompany(int id) throws ClientIntegrationException;
	
	public ClientTransfer getClientCompanyByCif(String cif) throws ClientIntegrationException;
	
	public boolean searchIndividual(String dni) throws ClientIntegrationException;
	
	public boolean searchCompany(String cif) throws ClientIntegrationException;
	
	public Vector<ClientTransfer> listClientIndividual() throws ClientIntegrationException;
	
	public Vector<ClientTransfer> listClientCompany() throws ClientIntegrationException;


	
}
