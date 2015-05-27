package message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessageStore {

        private static final List<Message> INSTANSE = Collections.synchronizedList(new ArrayList<Message>());
        private static final List<String> USRES =  new ArrayList<String>();

        private MessageStore() {
        }
        public static boolean addUser(String user){
            if(USRES.size()==0){
                USRES.add(user);
                return true;
            }
            else{
               for (int i=0;i<USRES.size();i++){
                   if (USRES.get(i).equals(user)) {
                       return false;
                   }
               }
                USRES.add(user);
                return true;
            }

        }

        public static void addMessage(Message message) {
            message.setId(INSTANSE.size()+1+"");
            INSTANSE.add(message);
        }

        public static void addAll(List<Message> messages) {
            INSTANSE.addAll(messages);
        }

        public static int getSize() {
            return INSTANSE.size();
        }

        public static List<Message> getSubMessagesByIndex(int index) {
            return INSTANSE.subList(index, INSTANSE.size());
        }

        public static Message getMessageById(String id) {
            for (Message message : INSTANSE) {
                if (message.getId().equals(id)) {
                    return message;
                }
            }
            return null;
        }
    public static int countOfMessages(int index){
        return INSTANSE.subList(index,INSTANSE.size()).size();
    }

}

