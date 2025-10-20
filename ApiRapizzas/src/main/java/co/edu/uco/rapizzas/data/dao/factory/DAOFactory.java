package co.edu.uco.rapizzas.data.dao.factory;

import java.sql.Connection;
import java.sql.SQLException;

import co.edu.uco.rapizzas.crosscuting.exception.RapizzasException;
import co.edu.uco.rapizzas.crosscuting.helper.SqlConnectionHelper;
import co.edu.uco.rapizzas.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.rapizzas.data.dao.entity.CategoryDAO;
import co.edu.uco.rapizzas.data.dao.entity.CustomerDAO;
import co.edu.uco.rapizzas.data.dao.entity.CustomerTableDAO;
import co.edu.uco.rapizzas.data.dao.entity.EmployeeDAO;
import co.edu.uco.rapizzas.data.dao.entity.IdentificationTypeDAO;
import co.edu.uco.rapizzas.data.dao.entity.OrderDAO;
import co.edu.uco.rapizzas.data.dao.entity.PaymentDAO;
import co.edu.uco.rapizzas.data.dao.entity.ProductDAO;
import co.edu.uco.rapizzas.data.dao.entity.ProductOrderDAO;
import co.edu.uco.rapizzas.data.dao.entity.SizeDAO;
import co.edu.uco.rapizzas.data.dao.entity.StatusDAO;
import co.edu.uco.rapizzas.data.dao.entity.TableDAO;
import co.edu.uco.rapizzas.data.dao.factory.postgresql.PostgreSQLDAOFactory;

public abstract class DAOFactory {
	
	protected Connection connection;
	protected static FactoryEnum factoryEnum = FactoryEnum.POSTGRESQL;
	
	public static DAOFactory getFactory() {
		if(FactoryEnum.POSTGRESQL.equals(factoryEnum)) {
			
			return new PostgreSQLDAOFactory();
		}else {
			var userMessage = MessagesEnum.USER_ERROR_FACTORY_NOT_INITIALIZED.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_FACTORY_NOT_VALIDATED.getContent();
			throw RapizzasException.create(userMessage, technicalMessage);
		}
	}

	public abstract CategoryDAO getCategoryDAO();
	
	public abstract SizeDAO getSizeDAO();
	
	public abstract StatusDAO getStatusDAO();
	
	public abstract TableDAO getTableDAO();
	
	public abstract CustomerDAO getCustomerDAO();
	
	public abstract CustomerTableDAO getCustomerTableDAO();
	
	public abstract EmployeeDAO getEmployeeDAO();
	
	public abstract IdentificationTypeDAO getIdentificationTypeDAO();
	
	public abstract OrderDAO getOrderDAO();
	
	public abstract PaymentDAO getPaymentDAO();
	
	public abstract ProductDAO getProductDAO();
	
	public abstract ProductOrderDAO getProductOrderDAO();
	
	protected abstract void openConnection();
	
	public final void initTransaction() {
		SqlConnectionHelper.ensureTransactionIsNotStarted(connection);
		try {
			connection.setAutoCommit(false);
		}catch(final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_STARTED.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_SQL_EXCEPTION_VALIDATING_TRANSACTION_IS_STARTED.getContent();
			throw RapizzasException.create(userMessage, technicalMessage);
		}catch(final Exception exception) {
			var userMessage =MessagesEnum.USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_STARTED.getContent();
			var technicalMessage =MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_STARTED.getContent();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}
	}
	
	public final void commitTransaction() {
		SqlConnectionHelper.ensureTransactionIsStarted(connection);
		try {
			connection.commit();
		 }catch (final SQLException exception){
			 var userMessage = MessagesEnum.USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_NOT_STARTED.getContent();
				var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_SQL_EXCEPTION_VALIDATING_TRANSACTION_IS_NOT_STARTED.getContent();
			 throw RapizzasException.create(exception,userMessage,technicalMessage);
		}catch(final Exception exception) {
			var userMessage =MessagesEnum.USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_NOT_STARTED.getContent();
			var technicalMessage =MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_NOT_STARTED.getContent();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}
	}
	
	public final void rollbackTransaction() {
		SqlConnectionHelper.ensureTransactionIsStarted(connection);
		try {
			connection.rollback();
		}catch (final SQLException exception){
			 var userMessage = MessagesEnum.USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_NOT_STARTED.getContent();
				var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_SQL_EXCEPTION_VALIDATING_TRANSACTION_IS_NOT_STARTED.getContent();
			 throw RapizzasException.create(exception,userMessage,technicalMessage);
		}catch(final Exception exception) {
			var userMessage =MessagesEnum.USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_NOT_STARTED.getContent();
			var technicalMessage =MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_NOT_STARTED.getContent();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}
	}
	
	public final void closeConnection() {
		SqlConnectionHelper.ensureConnectionIsOpen(connection);
		
		try {
			connection.close();
		}catch(final SQLException exception){
			 var userMessage=MessagesEnum.USER_ERROR_SQL_CONNECTION_IS_CLOSED.getContent();
			 var technicalMessage=MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_IS_CLOSED.getContent();
			 throw RapizzasException.create(exception,userMessage,technicalMessage);
		}catch(final Exception exception) {
			var userMessage =MessagesEnum.USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS.getContent();
			var technicalMessage =MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_SQL_EXCEPTION_VALIDATING_CONNECTION_STATUS.getContent();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}
	}
}
	
