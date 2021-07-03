package model.backup;

import model.base.BaseEntity;
import model.base.EntityInterface;

import javax.persistence.Entity;
import javax.persistence.Transient;


@Entity
public class BackupHistory extends BaseEntity implements EntityInterface {
    @Transient
    private static final long serialVersionUID = 6027546098461041916L;
    private long yedAlinmaTarihi;
    private String islem;//yedekleme veya yedekten geri alma seklinde olmalı
    private String yontemi;//manuel veya otomatik
    private String path;//yedekleme dosyasının adı ve pathi
    private boolean islemSonucu; //başarılı true, başarısız false;

    public BackupHistory() {
    }

    public BackupHistory(long yedAlinmaTarihi, String islem, String yontemi, String path, boolean islemSonucu) {
        this.yedAlinmaTarihi = yedAlinmaTarihi;
        this.islem = islem;
        this.yontemi = yontemi;
        this.path = path;
        this.islemSonucu = islemSonucu;
    }

    public long getYedAlinmaTarihi() {
        return yedAlinmaTarihi;
    }

    public void setYedAlinmaTarihi(long yedAlinmaTarihi) {
        this.yedAlinmaTarihi = yedAlinmaTarihi;
    }

    public String getIslem() {
        return islem;
    }

    public void setIslem(String islem) {
        this.islem = islem;
    }

    public String getYontemi() {
        return yontemi;
    }

    public void setYontemi(String yontemi) {
        this.yontemi = yontemi;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isIslemSonucu() {
        return islemSonucu;
    }

    public void setIslemSonucu(boolean islemSonucu) {
        this.islemSonucu = islemSonucu;
    }

    @Override
    public String toString() {
        return "BackupHistory{" +
                "backup date=" + yedAlinmaTarihi +
                ", process='" + islem + '\'' +
                ", method='" + yontemi + '\'' +
                ", path='" + path + '\'' +
                ", process result=" + islemSonucu +
                '}';
    }
}
