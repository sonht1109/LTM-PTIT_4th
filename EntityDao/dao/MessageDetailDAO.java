import java.math.BigInteger;

public class MessageDetailDAO extends DAO {

    public MessageDetailDAO() {
        super();
    }

    @SuppressWarnings("unchecked")
    public MessageDetail getMessageDetail(BigInteger id) {
        MessageDetail result = (MessageDetail) session.createQuery("from MessageDetail where id = " + id);
        return result;
    }

    public boolean editMessageDetail(MessageDetail params) {
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

    public boolean addMessageDetail(MessageDetail params) {
        DAO.session.save(params);
        return true;
    }

    public boolean deleteMessageDetail(MessageDetail params) {
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
