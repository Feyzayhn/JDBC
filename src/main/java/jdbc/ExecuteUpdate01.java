package jdbc;

import java.sql.*;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed2","postgres","221006-fyz.");
        Statement st = con.createStatement();


//1. Örnek: number_of_employees değeri ortalama çalışan sayısından az olan
// number_of_employees değerlerini 16000 olarak UPDATE edin.

    String sql1="UPDATE companies\n" +
            "SET number_of_employees = 16000\n" +
            "WHERE number_of_employees < (SELECT AVG(number_of_employees)\n" +
            "\t\t\t\t\t\t    FROM companies)";

    int updateSatirSayisi=st.executeUpdate(sql1); // bize int deger dondurur kac tane satir degistirdigini gosterir
                                                  // UPDATE edilen satir sayisini gosterir
        System.out.println("updateSatirSayisi = " + updateSatirSayisi);

        String sql2="SELECT * FROM companies";

        ResultSet result1 =st.executeQuery(sql2);

        while (result1.next()){
            System.out.println(result1.getInt(1)+"--"+result1.getString(2)+"--"+result1.getInt(3));
        }

        con.close();
        st.close();
    }
}
