package utility;

import model.base.EntityInterface;
import utility.enums.CommandTipi;

import java.util.List;

/**
 * bu class; istenen kriterlere göre birden fazla kolona göre filtreleme sağlanabilmesi için entity oluşturur
 */
public class MyPredicateCreator {
    private String colFirst;
    private String colSecond;
    private String colThird;
    private String value;
    private long beforeLong;
    private long afterLong;
    private EntityInterface entity;                    //Entity olarak arama yapmak için aslında ID ye bakmakta
    private List<EntityInterface> listForNotIn;        //bu not in için kullanılacak listede olmayan elemanları getir
    private CommandTipi command;                       //komut tipleri: Equal, NotEqual, Like,  Beetwen, NotIn, GreaterThan, IkiStrKolonTopla, Relation
    private boolean buPredicateGecerli;                //runtime zamanda üretilen bu predicatelerden sorunluları ayıklamak için
    private int predicateNo;                           //işlemeyen predicatenin kim olduğunun belirlenmesi için. Aslında bazen null olması gerekmekte
    //parametre olarak verilen listede olmayan elemanları getirecektir
    public MyPredicateCreator(String colFirst, List<EntityInterface> listForNotIn) {
        this.predicateNo = 1;
        this.colFirst = colFirst;
        this.listForNotIn = listForNotIn;
        this.command = CommandTipi.NotIn;
        if (listForNotIn != null) this.buPredicateGecerli = true;
    }

    public MyPredicateCreator(String colFirst) {
        this.colFirst = colFirst;
        this.command = CommandTipi.Max;
        this.buPredicateGecerli = true;
    }

    //tarih aramalarında kullanmak amacıyla örneğin son 24 saat verisi için beforeLong=(long) şimdi-24 şeklinde kullanılır
    public MyPredicateCreator(String colFirst, long longBeforOrAfter,CommandTipi cTipi) {
        if(cTipi!=CommandTipi.LessThan&&cTipi!=CommandTipi.GreaterThan) return; //yani geçersiz
        this.predicateNo = 2;
        this.colFirst = colFirst;
        this.beforeLong = longBeforOrAfter;
        this.command =cTipi;
        if (beforeLong > 0) this.buPredicateGecerli = true;
    }

    //iki tarih aralığını arayacaktır
    public MyPredicateCreator(String colFirst, long beforeLong, long afterLong) {
        this.predicateNo = 3;
        this.colFirst = colFirst;
        this.beforeLong = beforeLong;
        this.afterLong = afterLong;
        this.command = CommandTipi.Beetwen;
        this.buPredicateGecerli = true; //bir koşul bulamadım
    }


    // fieldde şu veri var mı şeklinde aramalar için sayı veya string olabilir ancak   equal veya like olmak zorunda
    public MyPredicateCreator(String colFirst, String value, CommandTipi komutTipi) {
        this.predicateNo = 4;
        if (komutTipi != CommandTipi.Equal && komutTipi != CommandTipi.Like && komutTipi != CommandTipi.NotEqual) return;
        if (isEmptyOrNull(value)) return;
        this.colFirst = colFirst;
        this.value = komutTipi == CommandTipi.Equal ? value : value.toUpperCase();
        this.command = komutTipi;
        this.buPredicateGecerli = true;
    }

    //Bu önemli çünkü burada field1 ve field2 entity fieldleridir ve stringleri toplanacaklar
    // ve Ör:entitide ad,soyad kolonları olsun kişi ararken adSoyad şeklinde tek stringle arayacaktır yada adres ararken
    public MyPredicateCreator(String colFirst, String colSecond, String seekingValue) {
        this.predicateNo = 5;
        if (isEmptyOrNull(seekingValue)) return;
        this.value = seekingValue.toUpperCase();
        this.colFirst = colFirst;
        this.colSecond = colSecond;
        this.command = CommandTipi.IkiStrKolonTopla;
        if (!isEmptyOrNull(colSecond)) this.buPredicateGecerli = true;
    }

    // kolonda entity aranır
    // Entity aranır yani ilişkili tablonun idsine bakar ancak id değil entity verilerek arama yapılır o entiti mevcutsa liste döner
    public MyPredicateCreator(String colFirst, EntityInterface entity) {
        this.predicateNo = 6;
        this.colFirst = colFirst;
        this.entity = entity;
        this.command = CommandTipi.Equal; // Equal Mecburi
        if (entity != null) this.buPredicateGecerli = true;
    }


    /**
     * ilişkili tablonun kolonuna göre like araması yapar  leftjoin ilişkisi kurulacaktır
     * sadece stringde çalışacaktır
     * @param colFirst Ana tablo kolonu
     * @param colSecond leftjoin olacak tablonun kolonu
     * @param colThird ve bidaha leftjoin olacak tablonun kolonu, kullanılmayacaksa null girilmeli
     * @param value son kolonda aranacak değer
     */
    public MyPredicateCreator(String colFirst, String colSecond, String colThird, String value) {
        this.predicateNo = 7;
        if(isEmptyOrNull(value))return;
        this.colFirst = colFirst;
        this.colSecond = colSecond;
        this.colThird=colThird;
        this.value = value.toUpperCase();
        this.command = CommandTipi.Relation;
        if ( !isEmptyOrNull(colFirst) && !isEmptyOrNull(colSecond)) //colThird null olabilir
            this.buPredicateGecerli = true;
    }


    private boolean isEmptyOrNull(String str) {
        return str == null || str.isEmpty();
    }

    public String getColThird() {
        return colThird;
    }

    public boolean isBuPredicateGecerli() {
        return buPredicateGecerli;
    }

    public int getPredicateNo() {
        return predicateNo;
    }

    public String getColSecond() {
        return colSecond;
    }

    public List<EntityInterface> getListForNotIn() {
        return listForNotIn;
    }

    public String getColFirst() {
        return colFirst;
    }

    public EntityInterface getEntity() {
        return entity;
    }

    public String getValue() {
        return value;
    }

    public CommandTipi getCommand() {
        return command;
    }

    public long getBeforeLong() {
        return beforeLong;
    }

    public long getAfterLong() {
        return afterLong;
    }

    @Override
    public String toString() {
        return "MyPredicateCreator{" +
                "colFirst='" + colFirst + '\'' +
                ", colSecond='" + colSecond + '\'' +
                ", colThird='" + colThird + '\'' +
                ", value='" + value + '\'' +
                ", beforeLong=" + beforeLong +
                ", afterLong=" + afterLong +
                ", entity=" + entity +
                ", listForNotIn=" + listForNotIn +
                ", command=" + command +
                ", thisPredicateValid=" + buPredicateGecerli +
                ", predicateNo=" + predicateNo +
                '}';
    }
}
