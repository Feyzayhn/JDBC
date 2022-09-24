package jdbcPractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Query04 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed2",
                "postgres",
                "221006-fyz.");

        //Statement st = con.createStatement(); bununlada olur BUNA 50 TANE GONDERSEM DATABASE DE 50 BİRİM YER KAPLAR
        // PreparedStatement hafizada daha az yer kaplar ve daha dinamik islemler yapabiliyoruz BU 1 BİRİM YER KAPLAR
                               // PreparedStatement daha guvenli


        PreparedStatement ps= con.prepareStatement("insert into ogrenciler values(?, ?, ?, ?)"); // EKLEME YAPACAGİZ DİNAMİK YAPİ OLUSTURDUK
        ps.setInt(1,200); // tablonun 1. sutunu icin numara atadik
        ps.setString(2,"Veli Can"); // tablonun 2. sutunu icin isim atadik
        ps.setString(3,"12"); // tablonun 3. sutunu icin sinifi atadik
        ps.setString(4,"E");  // 4. sutun icin cinsiyet atadik

        ps.executeUpdate(); // int dondurur kac veri girisi yapidigini
        System.out.println("Veri girisi yapildi");


    }
}
