package utility;

import org.junit.Test;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MyTimeConverterTest {
    @Test
    public void bhkjbjk() throws ParseException {
        String dt = "2008-03-11";  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(dt));
        //c.add(Calendar.DATE, 1);  // number of days to add
        dt = sdf.format(c.getTime());  // dt is now the new date
        System.out.println(c.getTime());
    }
    @Test
    public void calender(){
        String[] Aylar = {"", "Ocak", "Şubat", "Mart", "Nisan", "Mayıs", "Haziran",
                "Temmuz", "Ağustos", "Eylül", "Ekim", "Kasım", "Aralık"};

        /* Calendar yazdığınız anda sol tarafta uyarı çıkacak uyarıya tıklayın
        ve java.util.Calendar'ı projenize ekleyin */
        Calendar takvim = Calendar.getInstance();

        /* Tarihi al */
        int bugununTarihi = takvim.get(Calendar.DATE);

        /* Tarihi ekrana yaz */
        System.out.println("Bugün : " + takvim.get(Calendar.DATE));

        /* Tarihi değiştir, bir gün geri al */
        takvim.set(Calendar.DATE, bugununTarihi - 1);
        System.out.println("Dün   : " + takvim.get(Calendar.DATE));

        /* Tarihi bugüne tekrar geri al */
        takvim.set(Calendar.DATE, bugununTarihi);

        /* Tarihi string mesaj olarak ayarla */
        String tarih = takvim.get(Calendar.DATE) + "." + Aylar[takvim.get(Calendar.MONTH)]
                + "." + takvim.get(Calendar.YEAR);

        System.out.println(tarih);
    }
    @Test
    public void xx() throws ParseException {
        //new java.sql.Date(cal.getTimeInMillis()))
        final long now = System.currentTimeMillis();
        SimpleDateFormat sdf=new SimpleDateFormat ("yyyy/MM/dd");
       // java.util.Date utilDate = new java.util.Date();
        java.util.Date utilDate=sdf.parse("2011/02/22");
        System.out.println("utilDate:"+utilDate);
        Date sqlDate=new Date(utilDate.getTime());
        System.out.println("sqlDate:" +sqlDate);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(sqlDate);
        int yil=calendar.get(Calendar.YEAR);
        int ay=calendar.get(Calendar.MONTH)+1;
        int gun=calendar.get(Calendar.DATE);
        System.out.println(gun+"/"+ay+"/"+yil);
    }

    @Test
    public void stringToSqlTesti() throws ParseException {
        String dt = "2008-01-29";  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(dt));



        //c.add(Calendar.DATE, 0);  // number of days to add
        dt = sdf.format(c.getTime());
        System.out.println(dt);
    }

    @Test
    public void testStringToSqlDate() throws Exception {
       /* Date result = MyTimeConverter.stringToSqlDate("date");
        Assert.assertEquals(null, result);*/
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme