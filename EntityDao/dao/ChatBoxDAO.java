
public class ChatBoxDAO extends DAO {

    public ChatBoxDAO() {
        super();
    }

    @SuppressWarnings("unchecked")
    public ArrayList<ChatBox> searchChatBox(String key) {
        ArrayList<ChatBox> result = (ArrayList<ChatBox>) session.createQuery("from ChatBox where name like '%" + key + "%'").list();
        return result;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<ChatBox> getAll(Messenger mess) {
        ArrayList<ChatBox> result = (ArrayList<ChatBox>) session.createQuery("from ChatBox where ").list();
        return result;
    }

    public boolean editChatBox(ChatBox params) {
        try {
            Transaction tx = session.beginTransaction();
            session.update(params);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean addChatBox(ChatBox params) {
        DAO.session.save(params);
        return true;
    }

    public boolean deleteChatBox(ChatBox params) {
        try {
            Transaction tx = session.beginTransaction();
            DAO.session.delete(params);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
