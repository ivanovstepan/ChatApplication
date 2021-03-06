package message;

public class Message  {

    private String id;
    private String description;
    private String user;



    private boolean deleted;

    public Message(   String  id,String description,String user, boolean del) {
        this.id = id;
        this.description = description;
        this.user = user;
        this.deleted=del;
    }

    public String  getId() {
        return id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void setId( String  id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


    public String toString() {
        return "{\"id\":\"" + this.id + "\",\"description\":\"" + this.description + "\",\"user\":\"" + this.user + "\",\"delete\":\"" + this.isDeleted() + "\"}";
    }
}