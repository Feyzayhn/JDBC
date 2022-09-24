package jdbc;

import java.sql.*;

public class ExecuteQuery01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed2","postgres","221006-fyz.");
        Statement st = con.createStatement();


// 1. ORNEK SORU: companies tablosundan en yüksek ikinci number_of_employees değeri olan company
// ve number_of_employees değerlerini çağırın.

        String sql1="SELECT company, number_of_employees\t\n" +
                "FROM companies\n" +
                "ORDER BY number_of_employees DESC --buyukten kucuge siraladik\n" +
                "OFFSET 1 ROW  -- bir sutunu atladik\n" +
                "FETCH NEXT 1 ROW ONLY";

// 1.YOL OFFSET ve FETCH NEXT kullanarak
        ResultSet result1 = st.executeQuery(sql1); // calistirip datayi bize verecek
        while (result1.next()){
            System.out.println(result1.getString("company")+"---"+result1.getInt("number_of_employees"));
        }


// 2.YOL SUBQUERY KULLANARAK

        String sql2 = "SELECT company, number_of_employees\n" +
                "FROM companies\n" +
                "WHERE number_of_employees = (SELECT  MAX(number_of_employees)\n" +
                "                             FROM companies\n" +
                "                             WHERE number_of_employees < (SELECT MAX(number_of_employees)\n" +
                "                             FROM companies))";

        ResultSet result2 = st.executeQuery(sql2);
        while (result2.next()){
            System.out.println(result2.getString("company")+"---"+result2.getInt("number_of_employees"));
        }
        con.close();
        st.close();
        result1.close();
        result2.close();


    }
}
