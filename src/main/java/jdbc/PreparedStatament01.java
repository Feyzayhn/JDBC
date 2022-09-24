package jdbc;

import java.sql.*;

public class PreparedStatament01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed2", "postgres", "221006-fyz.");
        Statement st = con.createStatement();

        //1.ORNEK SORU: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.

    // 1. ADIM: Prepared statement query'sini olustur
        String sql1 = "UPDATE companies SET number_of_employees = ? WHERE company = ?";
                              // degistirilebilir degerler yerine ? koyduk dinamik olmasi icin
                              // Prepared= hazir demektir

    // 2.ADIM: PreparedStatement objesini olustur.
        PreparedStatement pst1 = con.prepareStatement(sql1); // bu sekilde obj olusturuyoruz
                                                            //  PreparedStatement data turunde olusturduk

    // 3.ADIM: set...() methodlari ile soru isaretleri icin deger gir.
        pst1.setInt(1,9999);  // 1. soru isaretine 9999 u girdik
        pst1.setString(2,"IBM"); // 2.soru isaretine IBM i girdik


        // 4.ADIM: Execute query
        int updateRowSayisi = pst1.executeUpdate(); // int deger donecek bu da kac update yapildigini soyleyecek
        System.out.println(updateRowSayisi+" satır güncellendi.");

        String sql2 = "SELECT * FROM companies";
        ResultSet result2 =  st.executeQuery(sql2);

        while (result2.next()){

            System.out.println(result2.getInt(1)+"--"+result2.getString(2)+"--"+result2.getInt(3));
        }


        // GOOGLE icin degisiklik

        pst1.setInt(1,15000);
        pst1.setString(2,"GOOGLE");

        int updateRowSayisi2 = pst1.executeUpdate();
        System.out.println(updateRowSayisi+" satır güncellendi.");

        String sql3 = "SELECT * FROM companies";
        ResultSet result3 =  st.executeQuery(sql3);

        while (result3.next()){

            System.out.println(result3.getInt(1)+"--"+result3.getString(2)+"--"+result3.getInt(3));
        }


        //2.ORNEK SORU: "SELECT * FROM <table name>" query'sini prepared statement ile kullanın

        read_data(con,"countries");
    }

    // Bir tablonun istenilen datasini prepared statement ile cegirmak icin kullanilan method
    public static void read_data(Connection con, String tableName){

       try {
           String query = String.format("SELECT * FROM %s", tableName); //format() methodu dinamik String olusturmak icin kullanilir
          // SQL quey'yi calistir
           Statement statement= con.createStatement();
          ResultSet rs=statement.executeQuery(query);
          while (rs.next()){ // Tum datayi cagiralim
              System.out.println(rs.getString(1)+"-"+rs.getString(2)+"-"+rs.getInt(3));
          }

       }catch(Exception e){
           System.out.println(e);
       }
    }
}