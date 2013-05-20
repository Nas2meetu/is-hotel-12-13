package is.hotelzargo.integracion.dao;

import is.hotelzargo.integracion.exception.ClientIntegrationException;
import is.hotelzargo.integracion.exception.ShiftIntegrationException;
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
    
    
    public ClientDAOImp(Connection connection){
    	this.connection = connection;
    	try {
			statement = connection.createStatement();
		} catch (SQLException e) {
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
			//Para crear el nuevo cliente se busca el maximo ID en ambas tablas
			//y se pone el maximo + 1
			int nextID = getNextID();
			
			statement.executeUpdate("INSERT INTO ClientIndividual (id,name, surname, dni, phone, creditCard, address) VALUES " +
					"('"+nextID+"','"+name+"', '"+surname+"', '"+dni+"', '"+phone+"', '"+creditCard+"', '"+address+"');" );
			
			//seteamos el ID obtenido
			t.setID(nextID);
			
		} catch (SQLException e) {
			e.getMessage();
			e.printStackTrace();
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
					
					//Para crear el nuevo cliente se busca el maximo ID en ambas tablas
					//y se pone el maximo + 1
					int nextID = getNextID();
					
					statement.executeUpdate("INSERT INTO ClientCompany (id,company, cif, phone, creditCard, address) VALUES " +
							"('"+nextID+"','"+company+"', '"+cif+"', '"+phone+"', '"+creditCard+"', '"+address+"');" );					
					
					//seteamos el ID obtenido
					t.setID(nextID);
					
										
				} catch (SQLException e) {
					e.getMessage();
					throw new ClientIntegrationException("Problema al crear cliente company");
				}
				
	}
	
	private int getNextID() throws ClientIntegrationException{
		
		//elegimos el id maximo de clientIndividual
		int maxIndividual = -1,maxCompany = -1;
		try {
			rs = statement.executeQuery("SELECT MAX(id) FROM ClientIndividual;");			
			//solo me devolvera 1 fila
			  while (rs.next()) {
					maxIndividual = rs.getInt(1);
			  }			

		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		} 
		
		//elegimos el id maximo de clientCompany
		try {
			rs = statement.executeQuery("SELECT MAX(id) FROM ClientCompany;");			
			//solo me devolvera 1 fila
				while (rs.next()) {
					maxCompany = rs.getInt(1);
				}			

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		//controlar que alguna tabla puede estar vacia,sin ids
		int max = Math.max(maxIndividual,maxCompany);
		//se devuelve el maximo + 1, para el siguiente ID unico
		return max+1;
		
	}


	@Override
	public void deleteClient(int id) throws ClientIntegrationException {
			
		//se busca en ambas tablas el id
		String QueryString = "SELECT * FROM ClientIndividual WHERE id='"+id+"';";
		  try {
			rs = statement.executeQuery(QueryString);			
			//solo me devolvera 1 fila
			  while (rs.next()) {
				  //TODO eliminar TODAS las dependencias de este cliente, reservas pendientes
				  return;
			  }
			
		  } catch (SQLException e) {
			e.printStackTrace();
			throw new ClientIntegrationException("Problema al eliminar cliente individual");				
		  }	
		  
			String QueryStringCompany = "SELECT * FROM ClientCompany WHERE id='"+id+"';";
			  try {
				rs = statement.executeQuery(QueryStringCompany);			
				//solo me devolvera 1 fila
				  while (rs.next()) {
					  //TODO eliminar TODAS las dependencias de este cliente, reservas pendientes
					  //esto es provisional
						String QueryDeleteCompany = "DELETE FROM ClientCompany WHERE id='"+id+"';";
						  try {
							  
							statement.executeUpdate(QueryDeleteCompany);			
							
						  } catch (SQLException e) {
							e.printStackTrace();
							throw new ClientIntegrationException("Problema al eliminar turno con ID "+id);				
						  }
					  
					  
					  
					  return;
				  }
				
			  } catch (SQLException e) {
				e.printStackTrace();
				throw new ClientIntegrationException("Problema al eliminar cliente individual");				
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
		//UPDATE
		String QueryString = "UPDATE ClientIndividual SET name='"+name+"',surname='"+surname+"'," +
				"dni='"+dni+"',phone='"+phone+"',creditCard='"+creditCard+"',address='"+address+"'  WHERE dni='"+dni+"';";
		  try {
			  
			statement.executeUpdate(QueryString);
			
		  } catch (SQLException e) {
			e.printStackTrace();
			throw new ClientIntegrationException("Problema al actualizar cliente "+dni);				
		  }
		
		
	}
	
	@Override
	public void updateClientCompany(ClientTransfer t) throws ClientIntegrationException {
		
		String company =((ClientTransferCompany) t).getCompany();
		String cif = ((ClientTransferCompany) t).getCIF();
		String phone = ((ClientTransferCompany) t).getPhone();
		String creditCard = ((ClientTransferCompany) t).getCreditCard();
		String address = ((ClientTransferCompany) t).getAddress();
		//UPDATE
		String QueryString = "UPDATE ClientCompany SET company='"+company+"'," +
				"cif='"+cif+"',phone='"+phone+"',creditCard='"+creditCard+"',address='"+address+"'  WHERE cif='"+cif+"';";
		  try {
			  
			statement.executeUpdate(QueryString);
			
		  } catch (SQLException e) {
			e.printStackTrace();
			throw new ClientIntegrationException("Problema al actualizar cliente compañía "+cif);				
		  }
	}
	
	@Override
	public ClientTransfer getClient(int id) throws ClientIntegrationException {
		//puede estar en cualquiera de las dos tablas, o individual o company
		ClientTransfer individual = getClientIndividual(id);
		ClientTransfer company = getClientCompany(id);
		if (individual != null){
			return individual;
		}
		else if (company != null){
			return company;
		}
		else{
			return null;
		}
		
	}


	private ClientTransfer getClientIndividual(int id) throws ClientIntegrationException {		
		
		String QueryString = "SELECT * FROM ClientIndividual WHERE id='"+id+"';";
		  try {
			rs = statement.executeQuery(QueryString);			
			//solo me devolvera 1 fila
			  while (rs.next()) {
				  
					String name = rs.getString(2);
					String surname = rs.getString(3);
					String dni = rs.getString(4);
					String phone = rs.getString(5);
					String creditCard = rs.getString(6);
					String address = rs.getString(7);
					
					ClientTransfer c = new ClientTransferIndividual(id,name, surname, dni, phone, creditCard, address);
					
					return c;
				  
			  }
			
		  } catch (SQLException e) {
			e.printStackTrace();
			throw new ClientIntegrationException("Problema al referenciar cliente individual con ID "+id);				
		  }	
		
		
		return null;
	}
	
	
	private ClientTransfer getClientCompany(int id) throws ClientIntegrationException {
		
		String QueryString = "SELECT * FROM ClientCompany WHERE id='"+id+"';";
		  try {
			rs = statement.executeQuery(QueryString);			
			//solo me devolvera 1 fila
			  while (rs.next()) {
				  
					String company = rs.getString(2);
					String cif = rs.getString(3);
					String phone = rs.getString(4);
					String creditCard = rs.getString(5);
					String address = rs.getString(6);
					
					ClientTransfer c = new ClientTransferCompany(id,company, cif, phone, creditCard, address);
					
					return c;
				  
			  }
			
		  } catch (SQLException e) {
			e.printStackTrace();
			throw new ClientIntegrationException("Problema al referenciar cliente company con ID "+id);				
		  }
		
		return null;
	}


	@Override
	public boolean searchClient(int id) throws ClientIntegrationException {
		
		//Se ha decidido usar ID unico para las dos tablas, así que este ID
		//puede estar en clientes individuales o en company, se busca en una tabla
		//y luego en otra
		if (searchIndividual(id)){
			return true;
		}
		else if (searchCompany(id)){
			return true;
		}
		else{
			return false;
		}
				
	}
	
	private boolean searchIndividual(int id) throws ClientIntegrationException{
		String QueryString = "SELECT * FROM ClientIndividual WHERE id='"+id+"';";
		  try {
			rs = statement.executeQuery(QueryString);
			//si existe, solo me devolvera 1 fila
			  while (rs.next()) {
				  return true;
			  }
			  
			  return false;
			
		  } catch (SQLException e) {
			e.getMessage();
			throw new ClientIntegrationException("Problema al buscar en tabla ClientIndividual");				
		  }
	}

	private boolean searchCompany(int id) throws ClientIntegrationException {
		
		String QueryString = "SELECT * FROM ClientCompany WHERE id='"+id+"';";
		  try {
			rs = statement.executeQuery(QueryString);
			//si existe, solo me devolvera 1 fila
			  while (rs.next()) {
				  return true;
			  }
			  
			 return false;
			
		  } catch (SQLException e) {
			e.getMessage();
			throw new ClientIntegrationException("Problema al buscar en tabla ClientCompany");				
		  }	
		
	}
	
	@Override
	public Vector<ClientTransfer> listClient() throws ClientIntegrationException {
		
		Vector<ClientTransfer> list = new Vector<ClientTransfer>();
		listClientIndividual(list);
		listClientCompany(list);
				
		return list; 
		
	}

	
	private void listClientIndividual(Vector<ClientTransfer> list) throws ClientIntegrationException {
		
		
		String QueryString = "SELECT * FROM ClientIndividual;";
		  try {
			rs = statement.executeQuery(QueryString);			
			
			  while (rs.next()) {

				  	int id = rs.getInt(1);
					String name = rs.getString(2);
					String surname = rs.getString(3);
					String dni = rs.getString(4);
					String phone = rs.getString(5);
					String creditCard = rs.getString(6);
					String address = rs.getString(7);
					
					ClientTransfer c = new ClientTransferIndividual(id,name, surname, dni, phone, creditCard, address);
					
					list.add(c);
				  
			  }
			
		  } catch (SQLException e) {
			e.printStackTrace();
			throw new ClientIntegrationException("Problema al listar clientes individuales");				
		  }
		
	}

	private void listClientCompany(Vector<ClientTransfer> list) throws ClientIntegrationException {
		
		String QueryString = "SELECT * FROM ClientCompany;";
		  try {
			rs = statement.executeQuery(QueryString);			
			  while (rs.next()) {
				  
				  	int id = rs.getInt(1);
					String company = rs.getString(2);
					String cif = rs.getString(3);
					String phone = rs.getString(4);
					String creditCard = rs.getString(5);
					String address = rs.getString(6);
					
					ClientTransfer c = new ClientTransferCompany(id,company, cif, phone, creditCard, address);
					
					list.add(c);
				  
			  }
			
		  } catch (SQLException e) {
			e.printStackTrace();
			throw new ClientIntegrationException("Problema al listar clientes company");				
		  }
		
	}
	
	private void closeAllConnections() throws SQLException{
		rs.close();
		statement.close();
		connection.close();
	}

	
}
