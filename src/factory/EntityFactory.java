package factory;

import model.*;
import model.backup.BackupHistory;
import model.backup.OtoBackup;
import model.base.EntityInterface;
import model.event.*;
import model.user.Groups;
import model.user.Permissionlar;
import model.user.Users;
import utility.enums.WPATH;
import utility.error.ErrorMessage;
import utility.mesajlar.Notify;

//factory pattern design
public class EntityFactory {


    public EntityInterface createEntity(WPATH modul) {
        switch (modul) {
            case person:return new Person();
            case faculty:return new Faculty();
            case department:return new Department();
            case clazz:return new Clazz();
            case note:return new Note();
            case student:return new Student();
            case personnel:return new Personnel();
            case professor:return new Professor();
            case appointment:return new Appointment();
            case available:return new Available();
            case studentNotes:
            case listStudentNotes:return new StudentNotes();
            case permissions:return new Permissionlar();
            case student_class:return new Student_Class();
            case eventData:return new EventData();
            case eventType:return new EventType();
            case eventTableDesc:return new EventTableDesc();
            case groups:return new Groups();
            case kullaniciTanimla:return new Users();
            case events:return new Events();
            case log: return new LOG_View();
            case otomatikYedekleme:return new OtoBackup();
            case backupHistory:return new BackupHistory();

            default:
                new Notify().showErrorNotify("Model generation error","it is not saved in entity factory class");
                ErrorMessage.FACTORY_CLASS_ERROR.printErrorMessages();
                return null;
        }
    }
}
