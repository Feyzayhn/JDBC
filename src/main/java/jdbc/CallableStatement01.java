package jdbc;

import java.sql.*;

public class CallableStatement01 {
    /*
    Java'da methodlar return type sahibi olsa da, void olsa da method olarak adlandirilir
    SQL'de ise data return ediyorsa "function" denir. Return yapmiyorsa "procedure" diye adlandirilir

     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed2","postgres","221006-fyz.");
        Statement st = con.createStatement();


        //1. Örnek: İki parametre ile çalışıp bu parametreleri toplayarak return yapan bir fonksiyon oluşturun.

        //1. Adım: Fonksiyon kodunu yaz.
        String sql1 = "CREATE OR REPLACE FUNCTION toplamaF(x NUMERIC, y NUMERIC)\n" + // REPLACE yazmamain sabebi her calistiginda yenilemesin daha once olusturdum diyorum
                "RETURNS NUMERIC\n" +
                "LANGUAGE plpgsql\n" +
                "AS\n" +
                "$$\n" +
                " BEGIN\n" +
                " \n" +
                " RETURN x+y;\n" +
                " \n" +
                " END\n" +
                "$$";

        //2. Adım: Fonksiyonu çalıştır.
        st.execute(sql1);

        //3. Adım: Fonksiyonu çağır.
        CallableStatement cst1 = con.prepareCall("{? = call toplamaF(?, ?)}"); // dinamik olmasi icin ? koyduk
               // con.prepareCall bu bize   CallableStatement dondurdugu icin buna atadik
        //4. Adım: Return için registerOutParameter() methodunu, parametreler için set() methodlarından uygun olanları kullan.
        cst1.registerOutParameter(1, Types.NUMERIC); // {? = call toplamaF(?, ?)} burada esitlikten onceki 1. ? yani yoplam deger icin yaptik
        cst1.setInt(2,15); // burada x icin
        cst1.setInt(3,25); // burada y icin

        //5. Adım: Çalıştırmak için execute() methodunu kullan.
        cst1.execute();

        //6. Adım: Sonucu çağırmak için return data tipine göre "get" methodlarından uygun olanı kullan.
        System.out.println(cst1.getBigDecimal(1));
                         // NUMERIC data tipleri getBigDecimal bu sekilde cagriliyor



// ========================================================================================


        //2. Örnek: Koninin hacmini hesaplayan bir function yazın.

        String sql2 = "CREATE OR REPLACE FUNCTION koniHacmi(r NUMERIC, h NUMERIC)\n" +
                "RETURNS NUMERIC\n" +
                "LANGUAGE plpgsql\n" +
                "AS\n" +
                "$$\n" +
                " BEGIN\n" +
                " \n" +
                " RETURN 3.14 * r * r * h / 3;\n" +  // koninin hacmini hesaplamayi yazdik
                " \n" +
                " END\n" +
                "$$";

        st.execute(sql2);
        CallableStatement cst2 = con.prepareCall("{? = call koniHacmi(?, ?)}");

        cst2.registerOutParameter(1, Types.NUMERIC);
        cst2.setInt(2,3);
        cst2.setInt(3,5);
        cst2.execute();
        System.out.println(cst2.getBigDecimal(1));
    }
}
