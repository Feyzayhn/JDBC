package jdbcPractice;

import static jdbcPractice.DatabaseUtilty.*;

public class Query06 {
    public static void main(String[] args) {

        createConnection(); // database class'indan cagirdik

        String query ="SELECT * FROM ogrenciler";
        System.out.println("Sutun Isimleri : " +getColumnNames(query));

        System.out.println("Okul No: " + getColumnData(query,"okul_no"));
        System.out.println("Ogrenci Ismi: " + getColumnData(query,"ogrenci_ismi"));
        System.out.println("Sinif: " + getColumnData(query,"sinif"));
        System.out.println("Cinsiyet: " + getColumnData(query,"cinsiyet"));


    }
}
