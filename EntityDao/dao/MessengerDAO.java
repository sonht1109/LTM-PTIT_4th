import java.math.BigInteger;

public class MessengerDAO extends DAO {

    public MessengerDAO() {
        super();
    }

    @SuppressWarnings("unchecked")
    public Messenger getMessenger(BigInteger id) {
        Messenger result = (Messenger) session.createQuery("from Messenger where id = " + id);
        return result;
    }

    public boolean editMessenger(Messenger params) {
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

    public boolean addMessenger(Messenger params) {
        DAO.session.save(params);
        return true;
    }

    public boolean deleteMessenger(Messenger params) {
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
