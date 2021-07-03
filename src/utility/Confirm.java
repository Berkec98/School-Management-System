package utility;

import utility.enums.OpType;
import utility.mesajlar.MyAlert;


public class Confirm {



    public static boolean isConfirmed(int adet, String eklenecekMesaj, OpType opType) {
        final String msg = "\n\n"+"Do you confirm the process "+opType.getStrType()+" ?" ;
        final String mesaj = adet == 0 ? msg : adet + " will be process\n\n DETAIL:(" + eklenecekMesaj + ")\n" + msg;
        final String title = "Warning!";
        return new MyAlert().isAlertResponseOK(mesaj, title);
    }
}