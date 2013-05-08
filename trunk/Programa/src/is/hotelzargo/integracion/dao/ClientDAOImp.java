package is.hotelzargo.integracion.dao;

import is.hotelzargo.integracion.DAOFactoryImp;
import is.hotelzargo.integracion.exception.ClientIntegrationException;
import is.hotelzargo.negocio.exception.ClientAppServicesException;
import is.hotelzargo.negocio.transfer.ClientTransfer;
import is.hotelzargo.negocio.transfer.ClientTransferCompany;
import is.hotelzargo.negocio.transfer.ClientTransferIndividual;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class ClientDAOImp implements ClientDAO {
	
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet rs = null;
    
  //TODO Gorka: todas las llamadas a la BBDD
    
    public ClientDAOImp(Connection connection){
    	this.connection = connection;
    	try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	@Override
	public void createClientIndividual(ClientTransfer t) throws ClientIntegrationException {
		
		String name =((ClientTransferIndividual) t).getName();
		String surname = ((ClientTransferIndividual) t).getSurname();
		String dni = ((ClientTransferIndividual) t).getDNI();
		String phone = ((ClientTransferIndividual) t).getPhone();
		String creditCard = ((ClientTransferIndividual) t).getCreditCard();
		String address = ((ClientTransferIndividual) t).getAddress();
		
		try {
			
			statement.executeUpdate("INSERT INTO ClientIndividual (name, surname, dni, phone, creditCard, address) VALUES " +
					"("+name+", "+surname+", "+dni+", "+phone+", "+creditCard+", "+address+");" );					
			
		} catch (SQLException e) {
			e.getMessage();
			throw new ClientIntegrationException("Problema al crear cliente individual");			
		}
		
	}

	
	@Override
	public void createClientCompany(ClientTransfer t) throws ClientIntegrationException {
				
				String company =((ClientTransferCompany) t).getCompany();
				String cif = ((ClientTransferCompany) t).getCIF();
				String phone = ((ClientTransferCompany) t).getPhone();
				String creditCard = ((ClientTransferCompany) t).getCreditCard();
				String address = ((ClientTransferCompany) t).getAddress();
				
				try {
					
					statement.executeUpdate("INSERT INTO ClientCompany (company, cif, phone, creditCard, address) VALUES " +
							"("+company+", "+cif+", "+phone+", "+creditCard+", "+address+");" );					
					
				} catch (SQLException e) {
					e.getMessage();
					throw new ClientIntegrationException("Problema al crear cliente company");
				}
				
	}


	@Override
	public void deleteClientIndividual(String id) throws ClientIntegrationException {
			
		String QueryString = "SELECT * FROM ClientIndividual WHERE dni="+id+";";
		  try {
			rs = statement.executeQuery(QueryString);
			
			//solo me devolvera 1 fila
			  while (rs.next()) {
				  //TODO eliminar TODAS las dependencias de este cliente, reservas pendientes
				  //habitaciones, etc....
			  }
			
		  } catch (SQLException e) {
			e.printStackTrace();
			throw new ClientIntegrationException("Problema al eliminar cliente individual");				
		  }	
			
	}
	
	@Override
	public void deleteClientCompany(String id) throws ClientIntegrationException {
			
			  String QueryString = "SELECT * FROM ClientCompany WHERE cif="+id+";";
			  try {
				rs = statement.executeQuery(QueryString);
				
				//solo me devolvera 1 fila
				  while (rs.next()) {
					  //TODO eliminar TODAS las dependencias de este cliente, reservas pendientes
					  //habitaciones, etc....
				  }
				
			  } catch (SQLException e) {
				e.printStackTrace();
				throw new ClientIntegrationException("Problema al eliminar cliente company");				
			  }			  
			
	}

	@Override
	public void updateClientIndividual(ClientTransfer t) throws ClientIntegrationException {
		
		String name =((ClientTransferIndividual) t).getName();
		String surname = ((ClientTransferIndividual) t).getSurname();
		String dni = ((ClientTransferIndividual) t).getDNI();
		String phone = ((ClientTransferIndividual) t).getPhone();
		String creditCard = ((ClientTransferIndividual) t).getCreditCard();
		String address = ((ClientTransferIndividual) t).getAddress();
		//TODO UPDATE
		//String QueryString = "UPDATE * FROM ClientCompany WHERE cif="+cif+";";
		 /* try {
			//rs = statement.executeQuery(QueryString);
			

			
		  } catch (SQLException e) {
			e.printStackTrace();
			throw new ClientIntegrationException("Problema al eliminar cliente company");				
		  }*/
		
		
	}
	
	@Override
	public void updateClientCompany(ClientTransfer t) throws ClientIntegrationException {
		
	}

	@Override
	public ClientTransfer getClientIndividual(int id) throws ClientIntegrationException {
		return null;
	}
	
	@Override
	public ClientTransfer getClientCompany(int id) throws ClientIntegrationException {
		return null;
	}

	@Override
	public boolean searchIndividual(String dni) throws ClientIntegrationException {
		
		String QueryString = "SELECT * FROM ClientIndividual WHERE dni="+dni+";";
		  try {
			rs = statement.executeQuery(QueryString);
			//si existe, solo me devolvera 1 fila
			  while (rs.next()) {
				  return true;
			  }
			  
			 return false;
			
		  } catch (SQLException e) {
			e.printStackTrace();
			throw new ClientIntegrationException("Problema al buscar cliente individual");				
		  }
		
	}

	@Override
	public boolean searchCompany(String cif) throws ClientIntegrationException {
		
		String QueryString = "SELECT * FROM ClientCompany WHERE cif="+cif+";";
		  try {
			rs = statement.executeQuery(QueryString);
			//si existe, solo me devolvera 1 fila
			  while (rs.next()) {
				  return true;
			  }
			  
			 return false;
			
		  } catch (SQLException e) {
			e.printStackTrace();
			throw new ClientIntegrationException("Problema al buscar cliente company");				
		  }	
		
	}

	@Override
	public Vector<ClientTransfer> listClientIndividual() throws ClientIntegrationException {
		Vector<ClientTransfer> list = new Vector<ClientTransfer>();
		
		return list;
	}
	
	@Override
	public Vector<ClientTransfer> listClientCompany() throws ClientIntegrationException {
		Vector<ClientTransfer> list = new Vector<ClientTransfer>();
		
		return list;
	}
	
	private void closeAllConnections() throws SQLException{
		rs.close();
		statement.close();
		connection.close();
	}

	
}
