package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import tikape.runko.domain.Keskustelu;
import tikape.runko.domain.Keskustelualue;
import tikape.runko.domain.Viesti;

public class KeskustelualueDao implements Dao<Keskustelualue, Integer> {

    private Database database;

    public KeskustelualueDao(Database database) {
        this.database = database;

    }

    @Override
    public Keskustelualue findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Keskustelualue WHERE id_keskustelualue = ?");
        stmt.setInt(1, key);

        ResultSet rs = stmt.executeQuery();

        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }
        Integer id = rs.getInt("id_keskustelualue");
        String nimi = rs.getString("nimi_keskustelualue");

        Keskustelualue k = new Keskustelualue(id, nimi);

        rs.close();
        stmt.close();
        connection.close();

        return k;

    }

    @Override
    public List<Keskustelualue> findAll() throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Keskustelualue");

        ResultSet rs = stmt.executeQuery();
        List<Keskustelualue> keskustelualueet = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id_keskustelualue");
            String nimi = rs.getString("nimi_keskustelualue");

            keskustelualueet.add(new Keskustelualue(id, nimi));
        }
        rs.close();
        stmt.close();
        connection.close();
        return keskustelualueet;

    }

    @Override
    public List<Keskustelualue> findAllIn(Collection<Integer> keys) throws SQLException {
        if (keys.isEmpty()) {
            return new ArrayList<>();
        }

        StringBuilder muuttujat = new StringBuilder("?");
        for (int i = 1; i < keys.size(); i++) {
            muuttujat.append(", ?");
        }

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Keskustelualue WHERE id_keskustelualue IN (" + muuttujat + ")");
        int laskuri = 1;
        for (Integer key : keys) {
            stmt.setObject(laskuri, key);
            laskuri++;
        }

        ResultSet rs = stmt.executeQuery();
        List<Keskustelualue> alueet = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id_keskustelualue");
            String nimi = rs.getString("nimi_keskustelualue");

            alueet.add(new Keskustelualue(id, nimi));
        }

        return alueet;
    }

    @Override
    public void add(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Keskustelualue VALUES (?, ?)");
        stmt.setInt(1, key);
        stmt.setString(2, "nimi_keskustelualue");
        stmt.close();
        connection.close();

    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
