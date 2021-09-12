import java.math.BigInteger;

public class ReactionDAO extends DAO {

    public ReactionDAO() {
        super();
    }

    @SuppressWarnings("unchecked")
    public Reaction getReaction(BigInteger id) {
        Reaction result = (Reaction) session.createQuery("from Reaction where id = " + id);
        return result;
    }

    public boolean editReaction(Reaction params) {
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

    public boolean addReaction(Reaction params) {
        DAO.session.save(params);
        return true;
    }

    public boolean deleteReaction(Reaction params) {
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
