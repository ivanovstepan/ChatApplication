package dao;

import message.Message;

import java.sql.SQLException;
import java.util.List;

public interface MessageDao {
	void add(Message message) throws SQLException;

	void update(Message message) throws SQLException;

	void delete(Message message) throws SQLException;

    void addUser (String user) throws SQLException;

	Message selectById(Message task);

	List<Message> selectAll();
}
