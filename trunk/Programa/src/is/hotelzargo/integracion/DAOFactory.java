package is.hotelzargo.integracion;

import is.hotelzargo.integracion.dao.ClientDAO;

public abstract class DAOFactory {

	private static DAOFactory instance = null;
	
	public static DAOFactory getInstance(){
		if(instance == null){
			instance = new DAOFactoryImp();
		}
		return (DAOFactoryImp) instance;
	}
	
	public abstract ClientDAO getClientDAO();
}
