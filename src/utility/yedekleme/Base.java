package utility.yedekleme;

import daolar.DaoRepositoryImp;
import model.backup.BackupHistory;
import utility.MyDate;

import java.util.Date;

public abstract class Base {
    final protected String IP = "localhost"; //ip de kullanılabilir
    final protected String user = "postgres";
    final protected String dbase = "ESistemDB";
    final protected String commandPath = "c:\\Program Files\\PostgreSQL\\11\\bin\\";
    protected final String password = "1123";

    public void sonucuKaydet(final String islem, final String yontemi, final String path, final boolean islemSonucu) {
        final DaoRepositoryImp<BackupHistory> daoBHist = new DaoRepositoryImp<>(BackupHistory.class);
        final long simdi = new MyDate(new Date()).getMyDateAsLong();
        final BackupHistory tmp = new BackupHistory(simdi, islem, yontemi, path, islemSonucu);
        daoBHist.save(tmp);
    }
}
