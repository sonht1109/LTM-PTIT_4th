import java.math.BigInteger;

public class FriendDAO extends DAO {

    public FriendDAO() {
        super();
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Friend> getAll(BigInteger id) {
        ArrayList<Friend> result = (ArrayList<Friend>) session.createQuery("from Friend where user_id_1 = " + id);
        return result;
    }

    public boolean editFriend(Friend params) {
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

    public boolean addFriend(Friend params) {
        DAO.session.save(params);
        return true;
    }

    public boolean deleteFriend(Friend params) {
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
