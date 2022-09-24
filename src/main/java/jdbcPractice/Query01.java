package jdbcPractice;

import java.sql.*;

public class Query01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1) Driver Yukle
        Class.forName("org.postgresql.Driver");

        //2) Baglanti olustur
        Connection con =DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed2","postgres","221006-fyz.");

        //3) Statament
        Statement st = con.createStatement(); // yukarida olusturdugumuz adresi buraya bagladik

        //4) ResultSet

        ResultSet veri= st.executeQuery("select * from ogrenciler"); // tabloyu bu setin icine atiyoruz

        //5) Sonuclari al
        while (veri.next()){   // tum datalar uzerinde gecis yapiyoruz

            // 1-index kullanarak sonuc alabilirim
            //System.out.println(veri.getInt(1) + veri.getString(2)
                             // + veri.getString(3)+ veri.getString(4));
                              // 1. index dedigimizde 1. sutundaki bilgiler geli

            // 2- sutun ismi kullanarak verileri alabilirim
            //System.out.println(veri.getInt("okul_no") + veri.getString("ogrenci_ismi")
                              // + veri.getString("sinif")+ veri.getString("cinsiyet"));


            System.out.printf("%-6d %-15.15s %-8s %-8s\n", veri.getInt(1), veri.getString(2),
                    veri.getString(3), veri.getString(4));  // bu sekil olarak daha duzgun cıktıgı iicn yukaridakini yoruma aldim
                                                                                  // printf ile duzenledik
        }

        // 6) Kapatma
        con.close();
        st.close();
        veri.close();
    }
}
