import java.math.BigInteger;

public class NotificationDAO extends DAO {

    public NotificationDAO() {
        super();
    }

    @SuppressWarnings("unchecked")
    public Notification getNotification(BigInteger id) {
        Notification result = (Notification) session.createQuery("from Notification where id = " + id);
        return result;
    }

    public boolean editNotification(Notification params) {
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

    public boolean addNotification(Notification params) {
        DAO.session.save(params);
        return true;
    }

    public boolean deleteNotification(Notification params) {
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
