package jdbcPractice;

import java.sql.*;

public class Query03 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed2",
                "postgres",
                "221006-fyz.");

        //Statement st = con.createStatement(); // bunu yerine PreparedStatement bunu kullandik

        PreparedStatement ps = con.prepareStatement("select * from ogrenciler");

        ResultSet rs = ps.executeQuery();

        ResultSetMetaData rsmd= rs.getMetaData();
        System.out.println("Sutun Sayisi : "+rsmd.getColumnCount());
                      // bu metaData ile daha fazla bilgi alabiliriz

        System.out.println("--------------------------------");

        System.out.println("1.Sutunun ismi"+rsmd.getColumnName(1)); // kolon isimlerini istiyoruz
        System.out.println("2.Sutunun ismi"+rsmd.getColumnName(2));
        System.out.println("3.Sutunun ismi"+rsmd.getColumnName(3));
        System.out.println("4.Sutunun ismi"+rsmd.getColumnName(4));

        System.out.println("--------------------------------");

        System.out.println("1.Sutunun Data Tipi: " + rsmd.getColumnTypeName(1));
        System.out.println("2.Sutunun Data Tipi: " + rsmd.getColumnTypeName(2));
        System.out.println("3.Sutunun Data Tipi: " + rsmd.getColumnTypeName(3));
        System.out.println("4.Sutunun Data Tipi: " + rsmd.getColumnTypeName(4));

        System.out.println("--------------------------------");

        System.out.println("Tablo ismi : " +rsmd.getTableName(1));
    }
}
