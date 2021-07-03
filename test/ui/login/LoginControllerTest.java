package ui.login;

import controller.LoginController;
import daolar.DaoRepositoryImp;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import model.backup.BackupHistory;
import model.backup.OtoBackup;
import utility.MyDate;
import utility.MyPredicateCreator;
import utility.enums.CommandTipi;

public class LoginControllerTest {


    @InjectMocks
    LoginController loginController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testHandleCancelButtonAction() throws Exception {
        loginController.handleCancelButtonAction();
    }

    @Test
    public void testHandleLoginButtonAction() throws Exception {
        final MyPredicateCreator yontem = new MyPredicateCreator("yontemi", "OTOMATİK", CommandTipi.Equal);
        final MyPredicateCreator tarih = new MyPredicateCreator("yedAlinmaTarihi");
        final DaoRepositoryImp<BackupHistory> daoBackupHistory = new DaoRepositoryImp<>(BackupHistory.class);
        Long sonTarih = daoBackupHistory.getMax(Long.class, tarih);
        //null dönme durumu oluyor
        System.out.println(new MyDate(sonTarih).getMyDateAsLong());
        DaoRepositoryImp<OtoBackup> daoOtoBackup = new DaoRepositoryImp<>(OtoBackup.class);
    }


}