package message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MessageStore {

        private static final List<Message> INSTANSE = Collections.synchronizedList(new ArrayList<Message>());

        private MessageStore() {
        }

        public static List<Message> getStorage() {
            return INSTANSE;
        }

        public static void addTask(Message message) {
            message.setId(INSTANSE.size()+1+"");
            INSTANSE.add(message);
        }

        public static void addAll(Message[] messages) {
            INSTANSE.addAll(Arrays.asList(messages));
        }

        public static void addAll(List<Message> messages) {
            INSTANSE.addAll(messages);
        }

        public static int getSize() {
            return INSTANSE.size();
        }
        public static void showAllMessages (){
            for (Message message : INSTANSE){
                System.out.println("{"+message.getUser()+"} : {"+message.getDescription()+"}");
            }
        }

        public static List<Message> getSubTasksByIndex(int index) {
            return INSTANSE.subList(index, INSTANSE.size());
        }

        public static Message getTaskById(String  id) {
            for (Message message : INSTANSE) {
                System.out.println(message+"  "+message.getId());
                if (message.getId().equals(id)) {
                    return message;
                }
            }
            return null;
        }

}

