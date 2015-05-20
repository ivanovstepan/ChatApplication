package dao;

import message.Message;

import java.util.List;

public interface MessageDao {
	void add(Message message);

	void update(Message message);

	void delete(Message message);

	Message selectById(Message task);

	List<Message> selectAll();
}
