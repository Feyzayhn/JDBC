package jdbcPractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Query05 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed2",
                "postgres",
                "221006-fyz.");

        Statement st = con.createStatement();

        // SORU: Ogrenciler tablosuna yeni bir kayit ekleyin (300, 'Sena Can', 12, 'K')

        int s1=st.executeUpdate("INSERT into ogrenciler VALUES (300, 'Sena Can', 12, 'K')"); // EKLEME YAPİYORUZ DİREK
                // executeUpdate bize int deger dondurur ve bize etkilenen satir sayisini dondurur
                // bunu gormek icin atama yapip yazdiririz
        System.out.println(s1);



        // SORU: Ogrenciler tablosuna birden fazla kayit ekleyin
        // (400, 'Sena Can', 12, 'K'),(401, 'Eda Can', 12, 'K'), (402, 'Ela Can', 12, 'K')
        // 1.YOL
        String [] veri ={"INSERT into ogrenciler VALUES (400, 'Sena Can', 12, 'K')",
                "INSERT into ogrenciler VALUES (401, 'Eda Can', 12, 'K')",
                "INSERT into ogrenciler VALUES (402, 'Ela Can', 12, 'K')"};

        int count=0; // bunu yapmasakta olurdu biz sadece kac data ekledigimizi sayalim diye yaptik
        for(String each:veri){
            count=count+st.executeUpdate(each);
        }
        System.out.println(count + "Data Eklendi");


        // 2.YOL
        String [] veri2 ={"INSERT into ogrenciler VALUES (500, 'Enes Can', 12, 'E')",
                "INSERT into ogrenciler VALUES (501, 'Feyza Can', 12, 'K')",
                "INSERT into ogrenciler VALUES (502, 'Cem Can', 12, 'E')"};

        for (String each: veri2
             ) {
            st.addBatch(each); // Yukaridaki datalarin hepsini birlestiriyor.
        }
        st.executeBatch();     // Datalari tek seferde gonderiyor.
    }
}
