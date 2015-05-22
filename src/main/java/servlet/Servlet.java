package servlet;

import dao.MessageDaoImpl;
import message.Message;
import message.MessageStore;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;
import util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static util.MessageUtil.*;

@WebServlet("/chat")
public class Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(Servlet.class.getName());
    private static List<Message> messageList;
    private MessageDaoImpl messageDao;
    @Override
    public void init() throws ServletException {
        try {
            this.messageDao = new MessageDaoImpl();
            loadHistory();
            logger.info("init finished");
        } catch (SAXException | IOException | ParserConfigurationException | TransformerException e) {
            logger.error(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("doGet");
        String token = request.getParameter(TOKEN);
        logger.info("Token " + token);
        if (token != null && !"".equals(token)) {
            int index = getIndex(token);
            if(MessageStore.countOfMessages(index)==0){
                logger.info("GET - Response status: 304");
                response.sendError(HttpServletResponse.SC_NOT_MODIFIED);
            }
            else {
                //logger.info("Index " + index);
                String tasks = formResponse(index);
                response.setCharacterEncoding(ServletUtil.UTF_8);
                response.setContentType(ServletUtil.APPLICATION_JSON);
                PrintWriter out = response.getWriter();
                out.print(tasks);
                out.flush();
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "'token' parameter needed");
            logger.error(HttpServletResponse.SC_BAD_REQUEST + "'token' parameter needed");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("doPost");
        String data = ServletUtil.getMessageBody(request);
        logger.info(data);
        try {
            JSONObject json = stringToJson(data);
            Message task = jsonToMessage(json);
            MessageStore.addMessage(task);
            logger.info(task.toString());
            //XMLHistory.addData(task);
            response.setStatus(HttpServletResponse.SC_OK);
            messageDao.add(task);
        } catch (ParseException /*| ParserConfigurationException | SAXException | TransformerException */e) {
            logger.error(e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            logger.error(HttpServletResponse.SC_BAD_REQUEST);
        }
        logger.info("doPost finished");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("doPut");
        String data = ServletUtil.getMessageBody(request);
        logger.info(data);
        try {
            JSONObject json = stringToJson(data);
            Message message = jsonToMessage(json);
            logger.info("message to update"+toString());
            String id = message.getId();
            Message taskToUpdate = MessageStore.getMessageById(id);
            if (taskToUpdate != null) {
                taskToUpdate.setDescription(message.getDescription());
                taskToUpdate.setUser(message.getUser());
                //XMLHistory.updateData(taskToUpdate);
                response.setStatus(HttpServletResponse.SC_OK);
                messageDao.update(message);
                logger.info("updated message: " + message.toString());
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Task does not exist");
            }
        } catch (ParseException /*| ParserConfigurationException | SAXException | TransformerException | XPathExpressionException*/ e) {
            logger.error(e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        logger.info("doPut finished");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("doDelete");
        String data = ServletUtil.getMessageBody(request);
        logger.info(data);
        try {
            JSONObject json = stringToJson(data);
            Message message = jsonToMessage(json);
            logger.info("message to delete"+toString());
            String id = message.getId();
            Message taskToUpdate = MessageStore.getMessageById(id);
            if (taskToUpdate != null) {
                taskToUpdate.setDescription(message.getDescription());
                taskToUpdate.setUser(message.getUser());
                taskToUpdate.setDeleted(true);
                //XMLHistory.updateData(taskToUpdate);
                response.setStatus(HttpServletResponse.SC_OK);
                messageDao.delete(message);

            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Task does not exist");
            }
        } catch (ParseException/* | ParserConfigurationException | SAXException | TransformerException | XPathExpressionException*/ e) {
            logger.error(e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        logger.info("doDelete finished");
    }

    @SuppressWarnings("unchecked")
    private String formResponse(int index) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(MESSAGES, MessageStore.getSubMessagesByIndex(index));
        jsonObject.put(TOKEN, getToken(MessageStore.getSize()));
        return jsonObject.toJSONString();
    }

    private void loadHistory() throws SAXException, IOException, ParserConfigurationException, TransformerException  {
        /*if (XMLHistory.doesStorageExist()) {
            MessageStore.addAll(XMLHistory.getMessages());
            MessageStore.showAllMessages();
        } else {
            XMLHistory.createStorage();
            addStubData();
        }*/
        MessageStore.addAll(messageDao.selectAll());

    }



}
