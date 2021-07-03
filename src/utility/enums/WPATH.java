package utility.enums;

/**
 Menu ve entitilerin tanımlandığı alan
 eğer girilen bir kayıt sadece database ile ilgili olmayan bir sınıf ise EntityName null bırakılmalı
 kayıt alması gereken ama bir arayüzü olmayan bir sınıf ise örneğin log amaçlı event takibi bu durumda ise Description boş bırakılmalı
 */

public enum WPATH {

    person("Person",null,null,null),
    faculty("Faculty","Register Faculties","/view/","faculty"),
    department("Department","Register Departments","/view/","department"),
    clazz("Clazz","Register Classes","/view/","clazz"),
    note("Note","Register Notes","/view/","note"),
    student("Student","Register Students","/view/","student"),
    personnel("Personnel","Register Personnels","/view/","personnel"),
    professor("Professor","Register Professors","/view/","professor"),
    appointment("Appointment","Get-Set an Appointment for Students","/view/","appointment"),
    select("Student","Select Student","/view/","studentSelect"),
    studentNotes("StudentNotes","Student Notes","/view/","studentNotes"),
    listStudentNotes("StudentNotes","Show One Student Notes","/view/","listStudentNotes"),
    available("Available","Register Available Time For Professors","/view/","availableTime"),
    //courseRegister("Available","Register Available Time For Professors","/view/","availableTime"),
    student_class("Student_Class","Course Selection","/view/","dersSecimi"),

    //board
    boardPersonReg(null,"Person Registration","/view/board/","personBoard"),
    uiBar(null,null,"/view/board/","uiBar"),
    setting(null,"Settings","/view/board/","setting"),


    //Login
    login(null,null,"/view/","login"),





    studentSec(null,"Select Student (ÖNEMLİ)","/view/","studentSelect"),
    log("LOG_View","Log","/view/","log"),


//events
    events("Events",null,null,null),
    eventTableDesc("EventTableDesc",null,null,null),
    eventType("EventType",null,null,null),
    eventData("EventData",null,null,null),

    groups("Groups","Register Groups","/view/","groups"),
    permissions("Permissionlar","Permissions (IMPORTANT)","/view/","permissions"),
    kullaniciTanimla("Users","Definition Users","/view/","kullaniciTanimla"),

    hakkinda(null,"About","/view/","hakkinda"),
//Mesajlar
    gecmisMesajlar(null,null,"/view/","gecmisMesajlar"),
//Yedekleme
    verileriYedekle(null,"Database Backup","/view/backup/","verileriYedekle"),
    verileriGeriYukle(null,"Restore Database","/view/backup/","verileriGeriYukle"),
    backupHistory("BackupHistory","Bakcup Restore History","/view/backup/","yedeklemeGecmisi"),
    otomatikYedekleme("OtoBackup","Database Auto Backup","/view/backup/","otomatikYedekleme");

    private String entityName;
    private String description;
    private String fxmlPath;
    private String fxmlFileName;

    WPATH(String entityName, String description, String fxmlPath,String fxmlFileName) {
        this.entityName = entityName;
        this.description = description;
        this.fxmlPath = fxmlPath;
        this.fxmlFileName=fxmlFileName;
    }

    public String getEntityName() {
        return entityName;
    }
    public String getDescription() {
        return description;
    }
    public String getFxmlPath() {
        return fxmlPath;
    }
    public String getFxmlFileName() {
        return fxmlFileName;
    }
}
