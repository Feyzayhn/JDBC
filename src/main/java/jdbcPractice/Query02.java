package jdbcPractice;

import java.sql.*;

public class Query02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed2",
                                                     "postgres",
                                                     "221006-fyz.");

        Statement st = con.createStatement();

        // SORU : Ogrenciler tablosundaki Erkek ogrencileri listeleyiniz
        ResultSet data = st.executeQuery("SELECT  * FROM ogrenciler WHERE cinsiyet='E'");


        while (data.next()){
            System.out.println(data.getInt(1)+data.getString(2)
                              +data.getString(3)+data.getString(4));
        }


    }
}
