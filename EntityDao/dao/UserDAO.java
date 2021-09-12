import java.math.BigInteger;

import entity.User;

public class UserDAO extends DAO {

    public UserDAO() {
        super();
    }

    @SuppressWarnings("unchecked")
    public User getUser(BigInteger id) {
        User result = (User) session.createQuery("from User where id = " + id);
        return result;
    }

    public boolean editUser(User params) {
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

    public boolean addUser(User params) {
        DAO.session.save(params);
        return true;
    }

    public boolean deleteUser(User params) {
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
