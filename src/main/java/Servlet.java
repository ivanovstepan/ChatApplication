

import history.XMLHistory;
import message.Message;
import message.MessageStore;
import org.apache.log4j.Logger;
import util.ServletUtil;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.io.PrintWriter;

import static util.MessageUtil.*;

@WebServlet("/chat")
public class Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(Servlet.class.getName());

    @Override
    public void init() throws ServletException {
        try {
            loadHistory();
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
            //System.out.println(index+" vot Index");
            logger.info("Index " + index);
            String tasks = formResponse(index);
            //System.out.println(tasks+" vot tasks");
            response.setContentType(ServletUtil.APPLICATION_JSON);
            PrintWriter out = response.getWriter();
            out.print(tasks);
            out.flush();
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "'token' parameter needed");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("doPost");
        String data = ServletUtil.getMessageBody(request);


        logger.info(data);
        try {
            JSONObject json = stringToJson(data);
            Message task = jsonToTask(json);
            MessageStore.addTask(task);
            XMLHistory.addData(task);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (ParseException | ParserConfigurationException | SAXException | TransformerException e) {
            logger.error(e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("doPut");
        String data = ServletUtil.getMessageBody(request);
        System.out.println(data+" vot");
        logger.info(data);
        try {
            JSONObject json = stringToJson(data);
            System.out.println(json+" vot json");
            Message task = jsonToTask(json);
            System.out.println(task+" vot task");

            String id = task.getId();
            System.out.println(id+" vot id");

            Message taskToUpdate = MessageStore.getTaskById(id);
            System.out.println(taskToUpdate+" vot tasktoupdata");

            if (taskToUpdate != null) {
                System.out.println("work1");
                taskToUpdate.setDescription(task.getDescription());
                System.out.println(taskToUpdate+" vot description");

                taskToUpdate.setUser(task.getUser());
                System.out.println(taskToUpdate+" vot user");

                XMLHistory.updateData(taskToUpdate);
                response.setStatus(HttpServletResponse.SC_OK);
                System.out.println(" GOOOD");

            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Task does not exist");
            }
        } catch (ParseException | ParserConfigurationException | SAXException | TransformerException | XPathExpressionException e) {
            logger.error(e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @SuppressWarnings("unchecked")
    private String formResponse(int index) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(TASKS, MessageStore.getSubTasksByIndex(index));
        jsonObject.put(TOKEN, getToken(MessageStore.getSize()));
        return jsonObject.toJSONString();
    }

    private void loadHistory() throws SAXException, IOException, ParserConfigurationException, TransformerException  {
        if (XMLHistory.doesStorageExist()) {
            MessageStore.addAll(XMLHistory.getMessages());
            MessageStore.showAllMessages();
        } else {
            XMLHistory.createStorage();
            addStubData();
        }
    }

    private void addStubData() throws ParserConfigurationException, TransformerException {
        Message[] stubTasks = {
                //new Message( "-1","Create markup","Anna" ),
                //new Message("2", "Learn JavaScript")
                };
        MessageStore.addAll(stubTasks);
        for (Message task : stubTasks) {
            try {

                XMLHistory.addData(task);

            } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
                logger.error(e);
            }
        }
    }

}
