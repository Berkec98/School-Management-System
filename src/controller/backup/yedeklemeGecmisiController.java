package controller.backup;


import utility.TableView.DFHelper;
import utility.TableView.DesiredField;
import utility.TableView.Table_View;
import model.backup.BackupHistory;
import utility.MyPredicateCreator;
import utility.enums.OpType;
import utility.enums.WPATH;

public class yedeklemeGecmisiController extends Table_View<BackupHistory> {

    public yedeklemeGecmisiController() {
        changeState(WPATH.backupHistory);
    }


    @Override
    protected void comboboxlariDoldur() {

    }



    protected DesiredField[] istenenAlanlariOlustur() {
        DFHelper dh = new DFHelper(new BackupHistory());
        DesiredField[] df = dh.buFieldleriOlustur("yedAlinmaTarihi","yedAlinmaTarihi", "islem", "yontemi",  "islemSonucu","path");
        dh.fieldBasliklariSunlarOlsun("Backup Date","Backup Time", "Operation Type", "Method", "Result","Path" );
        df[0].setDateTimeFormat("dd/MM/yyyy");
        df[1].setDateTimeFormat("HH:mm:ss");
        return df;
    }

    @Override
    public MyPredicateCreator[] filtrelemePredicateleriniOlustur() {
        return new MyPredicateCreator[0];
    }

    @Override
    protected void fromForm() {

    }

    @Override
    protected void toForm() {

    }

    @Override
    protected void clearFormFields() {

    }

    @Override
    protected boolean isErrorBeforeDatabase(OpType opType) {
        return false;
    }
}
