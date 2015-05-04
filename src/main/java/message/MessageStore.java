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

        public static List<Message> getSubTasksByIndex(int index) {
            return INSTANSE.subList(index, INSTANSE.size());
        }

        public static Message getTaskById(int  id) {
            for (Message message : INSTANSE) {
                if (message.getId().equals(id)) {
                    return message;
                }
            }
            return null;
        }

}

