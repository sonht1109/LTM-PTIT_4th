import java.math.BigInteger;

public class MessageDAO extends DAO {

    public MessageDAO() {
        super();
    }

    @SuppressWarnings("unchecked")
    public Message getMessage(BigInteger id) {
        Message result = (Message) session.createQuery("from Message where id = " + id);
        return result;
    }

    public boolean editMessage(Message params) {
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

    public boolean addMessage(Message params) {
        DAO.session.save(params);
        return true;
    }

    public boolean deleteMessage(Message params) {
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
