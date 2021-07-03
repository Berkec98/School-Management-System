package model.backup;

import model.base.BaseEntity;
import model.base.EntityInterface;
import utility.MyDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Date;

@Entity
public class OtoBackup extends BaseEntity implements EntityInterface {
    @Transient
    private static final long serialVersionUID = 7688772847518816422L;
    @Column(nullable = false)
    private String backupPath;  //backup adı ve yol tanımını içerir
    @Column(nullable = false)
    private String backupPeriod;
    @Column(nullable = false)
    private Date backupDate;

    public OtoBackup() {
    }

    public OtoBackup(String backupPath, String backupPeriod, Date backupDate) {
        this.backupPath = backupPath;
        this.backupPeriod = backupPeriod;
        this.backupDate = backupDate;
    }

    public String getTarih() {
        if (backupDate != null) {
            return new MyDate(backupDate).getMyDateAsString("dd/MM/yyyy");
        } else {
            return "null";
        }
    }


    public String getBackupPath() {
        return backupPath;
    }

    public void setBackupPath(String backupPath) {
        this.backupPath = backupPath;
    }

    public String getBackupPeriod() {
        return backupPeriod;
    }

    public void setBackupPeriod(String backupPeriod) {
        this.backupPeriod = backupPeriod;
    }

    public Date getBackupDate() {
        return backupDate;
    }

    public void setBackupDate(Date backupDate) {
        this.backupDate = backupDate;
    }

    @Override
    public String toString() {

        return " Backup will be made to "+backupPath+ " location as " +
                backupPeriod  + " from the date " +getTarih() +". ";
    }

}
