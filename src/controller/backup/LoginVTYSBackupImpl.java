package controller.backup;

import daolar.DaoRepositoryImp;
import utility.yedekleme.Backup;
import model.backup.BackupHistory;
import model.backup.OtoBackup;
import utility.MyDate;
import utility.MyPredicateCreator;
import utility.enums.CommandTipi;
import utility.mesajlar.MyAlert;
import utility.mesajlar.Notify;
import java.io.File;
import java.util.Date;
import java.util.List;

public class LoginVTYSBackupImpl {


    private void _vtysYedekle(Integer fark, String zamanDilimi, String ek, String path) {
        final String ek2 = "Data needs to be backed up according to your automatic backup plan.\n\n";
        final String ek3 = "should the backup process be done now?";
        final String mesaj = fark != null ? "en son yedek " + fark + " " + zamanDilimi + " önceye ait\n" + ek + ek2 + ek3 : ek2 + ek3;
        final String header = "CONFIRM TO TAKE A BACKUP";

        if (new MyAlert().isAlertResponseOK(mesaj, header)) {
            final String fileName = "Yed-" + new MyDate(new Date()).getMyDateAsString("dd-MM-yyyy");
            final Backup backup = new Backup();
            if (backup.backupIt(path + File.separator + fileName) != null) {
                final String title = "SUCCESSFUL BACKUP";
                final String text = "YOL= " + path + "\nDOSYA ADI= " + fileName + "\n\nYedek Alma Başarıyla Sonuçlandı";
                new Notify().showInfoNotify(title, text,9);
                backup.sonucuKaydet("BACKUP", "AUTOMATIC", path + File.separator + fileName, true);
            } else {
                final String title = "ATTENTION! ERROR OCCURRED";
                final String text = "Problem occurred during backup. Check it out";
                new Notify().showErrorNotify(title, text);
            }
        }
    }


    public LoginVTYSBackupImpl() {
    }


    private int _tarihiGuneCevir(Date date) {
        return date == null ? 0 : new MyDate(date).getMyDateAsLocalDateTime().getDayOfYear();
    }


    private int _tarihiGuneCevir(Long date) {
        return date == null ? 0 : new MyDate(date).getMyDateAsLocalDateTime().getDayOfYear();
    }


    private int _sonOtoYedGunu() { //null dönme sorunu yok null yerşne 0 döner
        final MyPredicateCreator yontem = new MyPredicateCreator("yontemi", "AUTOMATIC", CommandTipi.Equal);
        final MyPredicateCreator tarih = new MyPredicateCreator("yedAlinmaTarihi");
        final DaoRepositoryImp<BackupHistory> gecmisYedeklerDao = new DaoRepositoryImp<>(BackupHistory.class);
        return _tarihiGuneCevir(gecmisYedeklerDao.getMax(Long.class, tarih, yontem));//maxTarih==0 ise muhtemelen hiç otomatik yedekleme alınmadı. vtys den null dönüyor
    }


    /**
     * 1- oto backup listesi var mı eğer yoksa yedek alma talebi yoktur işlem yapılmayacak
     * 2-
     */
    public void vtysOtoYedZamaniysaYedekle() {
        final DaoRepositoryImp<OtoBackup> daoOtoBackup = new DaoRepositoryImp<>(OtoBackup.class);
        final List<OtoBackup> listOtoBackup = daoOtoBackup.getAll();
        if (listOtoBackup != null) {//oto backup listesi null ise hiç işlem yapmadan çıkacak
            final int simdikiGun = _tarihiGuneCevir(new Date());
            final int maxBackupHistGunu = _sonOtoYedGunu();
            final int fark = maxBackupHistGunu == 0 ? 0 : simdikiGun - maxBackupHistGunu; //eğer historyde OTOMATİK yoksa fark SIFIR olur
            for (OtoBackup ob : listOtoBackup) {  //ve yedeklemenin başlayacağı tarihi geçilmişmi baksın
                //Talep edilen gün
                final int otoBackupZamani = _tarihiGuneCevir(ob.getBackupDate());//VTYS de periyod tarihi null olamaz ayarlandı
                //bu blok ilk yedek alma zamanını
                if (otoBackupZamani < simdikiGun && maxBackupHistGunu != simdikiGun) {//aşağıdaki 3 durum içinde geçerli
                    _vtysYedekle(null, null, null, ob.getBackupPath());
                    return;
                }
                switch (ob.getBackupPeriod()) {
                    case "Daily":
                        if (fark > 0) _vtysYedekle(fark, "day", "Daily", ob.getBackupPath());
                        return;
                    case "Weekly":
                        if (fark > 6) _vtysYedekle(fark / 7, "week", "Weekly", ob.getBackupPath());
                        return;
                    case "Monthly":
                        if (fark > 29) _vtysYedekle(fark / 30, "month", "Monthly", ob.getBackupPath());
                        return;
                }
            }
        }
    }
}
