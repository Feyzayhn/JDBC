package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1. Adim : Driver'a kaydol
        Class.forName("org.postgresql.Driver"); // org.postgresql bu class dan Driver a baglandik

        // 2. Adim : Database'e baglan SQL E baglandik
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed2","postgres","221006-fyz.");
                                             // Burada jdbc SQL İMİN ADRESİ
        // 3. Adim : Statement olustur.
        Statement st =con.createStatement();

        // 4. Adim : Query calistir.

        //1.Example: "Workers" adında bir table oluşturup "worker_id,worker_name, worker_salary" sütunlarını ekleyin.
         String sql1="CREATE TABLE workers (worker_id VARCHAR(50),worker_name Varchar(50),worker_salary INT)";
         boolean resualt = st.execute(sql1);
         //System.out.println(resualt); // False return yapar,cunku data cagrilmadi

        //2.Örnek: Table'a worker_address sütunu ekleyerel alter yapın.
        String sqi2="ALTER TABLE workers ADD worker_address VARCHAR(80) ";
        st.execute(sqi2);


        //3.Example: Drop workers table
        String sql3="DROP TABLE workers";
        st.execute(sql3);

        // 5. Adim: Baglanti ve Statement 'ı kapat.
        con.close(); // burada int baglantisini kestik
        st.close();



    }
}
