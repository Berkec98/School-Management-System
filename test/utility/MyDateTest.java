package utility;

import org.junit.Test;



public class MyDateTest {


    @Test
    public void yenileritestEt(){
        MyDate mDateUtilDate=new MyDate(new java.util.Date());
        System.out.println(mDateUtilDate.getMyDateAsLong());
        MyDate md=new MyDate(1569944672013L);
        System.out.println(md.getMyDateAsString("dd/MM/yyyy"));
    }
    @Test
    public void testSetMyDate() throws Exception {
        System.out.println("Tarih olarak UTIL tarih olarak girilince gelen sonuçlar");
        MyDate mDateUtilDate=new MyDate(new java.util.Date());
        System.out.println(mDateUtilDate.getMyDateAsString("MM:hh:ss"));
        /*System.out.println("Test1");
        System.out.println("tarih olarak INTEGER Gün:11 Ay:1 Yıl:2016 girilince gelen sonuçlar");
        MyDate mDate=new MyDate(11,1,2016);
        System.out.println("MyDate test Util Dateyi Yazdır    :" + mDate.getMyDateAsUtilDate());
        System.out.println("MyDate test SQL Dateyi Yazdır     :"+mDate.getMyDateAsSQLDate());
        System.out.println("MyDate test String olarak Yazdır  :"+mDate.getMyDateAsString("dd/MM/yyyy"));
        System.out.println("MyDate test String olarak Yazdır  :"+mDate.getMyDateAsString("yyyy/MM/dd"));
        System.out.println("MyDate test Long olarak Yazdır    :"+mDate.getMyDateAsLong());

        System.out.println("\nTest1_Zero İnt");
        System.out.println("tarih olarak INTEGER null girilince gelen sonuçlar");
        int day=0,month=0,year=0;
        MyDate mDateE=new MyDate(day,month,year);
        System.out.println("MyDate test Util Dateyi Yazdır    :" + mDateE.getMyDateAsUtilDate());
        System.out.println("MyDate test SQL Dateyi Yazdır     :"+mDateE.getMyDateAsSQLDate());
        System.out.println("MyDate test String olarak Yazdır  :"+mDateE.getMyDateAsString("dd/MM/yyyy"));
        System.out.println("MyDate test String olarak Yazdır  :"+mDateE.getMyDateAsString("yyyy/MM/dd"));
        System.out.println("MyDate test Long olarak Yazdır    :"+mDateE.getMyDateAsLong());

        System.out.println("\nTest2");
        System.out.println("Tarih olarak STRING  11/01/2016, dd/MM/yyyy olarak girilince gelen sonuçlar");
        MyDate mDateString=new MyDate("11/01/2016","dd/MM/yyyy");
        System.out.println("MyDate test Util Dateyi Yazdır    :"+mDateString.getMyDateAsUtilDate());
        System.out.println("MyDate test SQL Dateyi Yazdır     :"+mDateString.getMyDateAsSQLDate());
        System.out.println("MyDate test String olarak Yazdır  :"+mDateString.getMyDateAsString("dd/MM/yyyy"));
        System.out.println("MyDate test String olarak Yazdır  :"+mDateString.getMyDateAsString("yyyy/MM/dd"));
        System.out.println("MyDate test Long olarak Yazdır    :"+mDateString.getMyDateAsLong());

        System.out.println("\nTest3");
        System.out.println("Tarih olarak SQL tarih olarak girilince gelen sonuçlar");
        MyDate mDateSQLDate=new MyDate(new java.sql.Date(new java.util.Date().getTime()));
        System.out.println("MyDate test Util Dateyi Yazdır    :"+mDateSQLDate.getMyDateAsUtilDate());
        System.out.println("MyDate test SQL Dateyi Yazdır     :"+mDateSQLDate.getMyDateAsSQLDate());
        System.out.println("MyDate test String olarak Yazdır  :"+mDateSQLDate.getMyDateAsString("dd/MM/yyyy"));
        System.out.println("MyDate test String olarak Yazdır  :"+mDateSQLDate.getMyDateAsString("yyyy/MM/dd"));
        System.out.println("MyDate test Long olarak Yazdır    :"+mDateSQLDate.getMyDateAsLong());

        System.out.println("\nTest4");
        System.out.println("Tarih olarak UTIL tarih olarak girilince gelen sonuçlar");
        MyDate mDateUtilDate=new MyDate(new java.util.Date());
        System.out.println("MyDate test Util Dateyi Yazdır    :"+mDateUtilDate.getMyDateAsUtilDate());
        System.out.println("MyDate test SQL Dateyi Yazdır     :"+mDateUtilDate.getMyDateAsSQLDate());
        System.out.println("MyDate test String olarak Yazdır  :"+mDateUtilDate.getMyDateAsString("dd/MM/yyyy"));
        System.out.println("MyDate test String olarak Yazdır  :"+mDateUtilDate.getMyDateAsString("yyyy/MM/dd"));
        System.out.println("MyDate test Long olarak Yazdır    :"+mDateUtilDate.getMyDateAsLong());

        System.out.println("\nTest5");
        System.out.println("Problemli Tarih Girişleri NULL Tarih Gönderiliyor NULL POİNTER amaçlı");
        java.util.Date uDate = null;
        MyDate mDateNull=new MyDate(uDate);
        System.out.println("MyDate test Util Dateyi Yazdır    :"+mDateNull.getMyDateAsUtilDate());
        System.out.println("MyDate test SQL Dateyi Yazdır     :"+mDateNull.getMyDateAsSQLDate());
        System.out.println("MyDate test String olarak Yazdır  :"+mDateNull.getMyDateAsString("dd/MM/yyyy"));
        System.out.println("MyDate test String olarak Yazdır  :"+mDateNull.getMyDateAsString("yyyy/MM/dd"));
        System.out.println("MyDate test Long olarak Yazdır    :"+mDateNull.getMyDateAsLong());

        System.out.println("\nTest3");
        System.out.println("Tarih olarak STRING  NULL, dd/MM/yyyy olarak girilince gelen sonuçlar");
        MyDate mDateStringNull=new MyDate(null,"dd/MM/yyyy");
        System.out.println("MyDate test Util Dateyi Yazdır    :"+mDateStringNull.getMyDateAsUtilDate());
        System.out.println("MyDate test SQL Dateyi Yazdır     :"+mDateStringNull.getMyDateAsSQLDate());
        System.out.println("MyDate test String olarak Yazdır  :"+mDateStringNull.getMyDateAsString("dd/MM/yyyy"));
        System.out.println("MyDate test String olarak Yazdır  :"+mDateStringNull.getMyDateAsString("yyyy/MM/dd"));
        System.out.println("MyDate test Long olarak Yazdır    :"+mDateStringNull.getMyDateAsLong());*/
    }

    @Test
    public void testSetMyDate2() throws Exception {
/*        myDate.setMyDate(12, 2, 2016, "dd/MM/yyyy");
        System.out.println(myDate.getTarih());*/
    }
}

