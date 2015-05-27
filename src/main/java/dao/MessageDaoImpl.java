package dao;

import db.ConnectionManager;
import message.Message;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageDaoImpl implements MessageDao {
	private static Logger logger = Logger.getLogger(MessageDaoImpl.class.getName());

	@Override
	public void add(Message message) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
            connection = ConnectionManager.getConnection();
            connection.setAutoCommit(false);
            Date utilDate = new java.util.Date();
			preparedStatement = connection.prepareStatement("INSERT INTO messages (id, text, date, user_name, deleted) VALUES (?, ?, ?, ?, ?)");
			preparedStatement.setInt(1, Integer.parseInt(message.getId()));
			preparedStatement.setString(2, message.getDescription());
            preparedStatement.setDate(3, new java.sql.Date(utilDate.getTime()));
            preparedStatement.setString(4, message.getUser());
			preparedStatement.setBoolean(5, message.isDeleted());
			preparedStatement.executeUpdate();
            connection.commit();
		} catch (SQLException e) {
            connection.rollback();
			logger.error(e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		}
	}

	@Override
	public void update(Message message) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionManager.getConnection();
            connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement("Update messages SET text = ?, deleted = ? WHERE id = ?");
			preparedStatement.setString(1, message.getDescription());
			preparedStatement.setBoolean(2, message.isDeleted());
			preparedStatement.setInt(3, Integer.parseInt(message.getId()));
			preparedStatement.executeUpdate();
            connection.commit();
		} catch (SQLException e) {
            connection.rollback();
			logger.error(e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		}
	}

	@Override
	public Message selectById(Message task) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Message> selectAll() {
		List<Message> messages = new ArrayList<>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionManager.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM messages");
			while (resultSet.next()) {
				String id = resultSet.getString("id");
				String description = resultSet.getString("text");
                String user = resultSet.getString("user_name");
				boolean done = resultSet.getBoolean("deleted");
				messages.add(new Message(id, description, user, done));
			}
		} catch (SQLException e) {
			logger.error(e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		}
		return messages;
	}

	@Override
	public void delete(Message message) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionManager.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement("Update messages SET text = ?, deleted = ? WHERE id = ?");
            preparedStatement.setString(1, message.getDescription()+"(deleted)");
            preparedStatement.setBoolean(2, true);
            preparedStatement.setInt(3, Integer.parseInt(message.getId()));
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }
        }
	}
    @Override
    public void addUser(String user) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionManager.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement("INSERT INTO users (name) VALUES (?)");
            preparedStatement.setString(1,user);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }
        }
    }

}
