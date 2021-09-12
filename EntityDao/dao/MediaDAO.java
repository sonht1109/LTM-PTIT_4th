import java.math.BigInteger;

public class MediaDAO extends DAO {

    public MediaDAO() {
        super();
    }

    @SuppressWarnings("unchecked")
    public Media getMedia(BigInteger id) {
        Media result = (Media) session.createQuery("from Media where id = " + id);
        return result;
    }

    public boolean editMedia(Media params) {
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

    public boolean addMedia(Media params) {
        DAO.session.save(params);
        return true;
    }

    public boolean deleteMedia(Media params) {
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
