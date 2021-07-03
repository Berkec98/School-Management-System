package utility;


import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;

/**
 * TARİH CONVERT CLASS
 * Bu sınıf; tarih değerini kullanıcıdan almakta ve kendisinde olan myDate değşkenine yükleyip daha sonra kullanıcıya tarih olarak sunmaktadır
 * sınıfın avantajları ise aldığı tarihin kısıtlı olmaması hemen hemen bütün tarih formatlarını alabilmesi ve kullanıcının istediği tarih türüne dönüştürmesi
 * <p>
 * <p>
 * A) Kabul Ettiği Tarih Türleri:   1) MyDate(util.Date)
 * 2) MyDate(LocalDate)
 * (YAPILANDIRICILARI)           3) MyDate(sql.Date)
 * 4) MyDate(String date, String format)
 * 5) MyDate(int gun, int ay, int yil)
 * 6) MyDate(Long date)
 * <p>
 * <p>
 * B) Getter Metodları              1) getMyDateAsCalendar()
 * 2) getMyDateAsLocalDate()
 * 3) getMyDateAsUtilDate()
 * 4) getMyDateAsSQLDate()
 * 5) getMyDateAsString(String format)
 * 6) getMyDateAsLong()
 * 7) getGun()
 * 8) getAy()
 * 9) getYil()
 * <p>
 * <p>
 * C) Diğer Metodları               1) void add(int whatAdd, int piece)
 * 2) Long diffDates(Date dateCikarilan)
 */

public class MyDate {
    private java.util.Date myDate;

    public MyDate(java.util.Date myDate) {
        this.myDate = myDate;
    }

    public MyDate(LocalDate localDate) {
        if (localDate == null)
            this.myDate = null;
        else
            this.myDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public MyDate(LocalDateTime localDateTime) {
        ZonedDateTime z = localDateTime.atZone(ZoneId.systemDefault());
        try {
            this.myDate = Date.from(z.toInstant());
        } catch (Exception e) {
            this.myDate = null;
        }
    }

    public MyDate(java.sql.Date myDate) {
        try {
            this.myDate = new Date(myDate.getTime());
        } catch (Exception e) {
            this.myDate = null;
        }
    }

    public MyDate(Long date) {
        try {
            myDate = new Date(date);
        } catch (Exception e) {
            this.myDate = null;
        }
    }

    /*
    Bu metod MyDate(String date, String format) yapılandırıcısı tarafından kullanılmaktadır
     format üret fonksiyonu sayesinde kullanıcının runtime gireceği her format kabul edilecektir
     fonksiyon işaret olarak * karakterini aramaktadır
     örnek:
     12/10/2019
     5/5/2005
     12-10-2019
     12.10.2019*/
    private String sembolleriDuzelt(String girilenTarih) {
        girilenTarih=girilenTarih.replace(".", "/"); //girilen tarihteki  nokta ve tireler / ile değiştiriliyor
        girilenTarih=girilenTarih.replace("-", "/");
        return girilenTarih;
    }


    //format üret fonksiyonu sayesinde kullanıcının runtime gireceği her format kabul edilecektir
    public MyDate(String date, String format) {
        if(date==null||date.isEmpty()){
            this.myDate=null;
            return;
        }
        date=sembolleriDuzelt(date.trim());
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            this.myDate = sdf.parse(date);
        } catch (Exception ex) {
            //myDate=null;       //önemli silme.. bazı yerler buna bakarak karar veriyor
            this.myDate = null;
        }
    }

    public MyDate(int gun, int ay, int yil) {
        if (gun < 1 || gun > 31 || ay < 1 || ay > 12 || yil < 2000) {
            myDate = null;
            return;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, gun);
        calendar.set(Calendar.MONTH, ay - 1);
        calendar.set(Calendar.YEAR, yil);
        this.myDate = calendar.getTime();
    }


    public Calendar getMyDateAsCalendar() {

        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(myDate);
        } catch (Exception e) {
            return null;
        }
        return cal;
    }


    public LocalDateTime getMyDateAsLocalDateTime() {
        return myDate == null ? null : LocalDateTime.ofInstant(myDate.toInstant(), ZoneId.systemDefault());
    }

    public LocalDate getMyDateAsLocalDate() {
        return myDate == null ? null : Instant.ofEpochMilli(myDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    //myDate değişkenini olduğu gibi gönderir. string bir ifadenin doğru formatta girildeğini kontrol için idealdir
    public java.util.Date getMyDateAsUtilDate() {
        return myDate;
    }

    public java.sql.Date getMyDateAsSQLDate() {
        return myDate == null ? null : new java.sql.Date(myDate.getTime());
    }

    public String getMyDateAsString(String format) {
        if (myDate == null) return null;
        SimpleDateFormat sdf;
        try {
            sdf = new SimpleDateFormat(format);
        } catch (Exception e) {
            return null;
        }
        return sdf.format(this.myDate);
    }


    public Long getMyDateAsLong() {
        return myDate == null ? null : myDate.getTime();
    }

    public int getGun() {
        return myDate == null ? -1 : getMyDateAsCalendar().get(Calendar.DAY_OF_MONTH);
    }

    public int getAy() {
        return myDate == null ? -1 : getMyDateAsCalendar().get(Calendar.MONTH);

    }

    public int getYil() {
        return myDate == null ? -1 : getMyDateAsCalendar().get(Calendar.YEAR);
    }

    /**
     * mevcut mydate değişkenine tarih veya saat ekleme veya çıkarmak için kullanılır
     * eğer mydate null ise hata oluşacaktır bu sebeple try catch ile korunması gerekir
     * @param whatAdd : neyin eklenmesi-çıkarılması isteniyorsa o belirtilir Calendar.HOUR_OF_DAY,
     * @param piece   :  miktarı çıkarmak iiçin eksi sayı verilmesi gerekir.
     */
    public void add(int whatAdd, int piece) { //Calender.HOURS
        Calendar c = Calendar.getInstance();
        c.setTime(myDate);
        c.add(whatAdd, piece);
        myDate = c.getTime();
    }

    /**
     * long olarak alınan tarihin toplam kaç ay,gün,yıl,hafta olduğunu veren fonksiyonlar
     * <p>
     * Birimler          MiliSecond(yani Long) karşılığı
     * ----------------------------------------------------
     * 1-MiliSecond         1L
     * 1-Second             1000L
     * 1-Minute             60000L
     * 1-Hour               3600000L
     * 1-Day                86400000L
     * 1-Week               604800000L
     * 1-Month              2592000000L
     * 1-Year               31104000000L
     */


    private static long toSeconds(long date) {
        return date / 1000L;
    }

    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    private static long toHours(long date) {
        return toMinutes(date) / 60L;
    }

    private static long toDays(long date) {
        return toHours(date) / 24L;
    }

    private static long toMonths(long date) {
        return toDays(date) / 30L;
    }


}