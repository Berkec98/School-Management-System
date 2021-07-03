package utility.yedekleme;

import java.io.IOException;

public class Backup extends Base {

    public Process backupIt(String fileAndPath) {
        // pg_dump -i -h localhost -p 5432 -U postgres -F c -b -v -f "/usr/local/backup/10.70.0.61.backup" old_db
        try {
            //F olarak t verildi yani tar dosya biçimi
            final ProcessBuilder pb =
                    new ProcessBuilder(commandPath + "pg_dump.exe", "-f", fileAndPath, "-F", "t", "-v", "-h", IP, "-U", user, dbase);
            pb.environment().put("PGPASSWORD", password);
            pb.redirectErrorStream(true);
            return pb.start();
        } catch (IOException ex) {
            ex.getMessage();
            ex.printStackTrace();
            return null;
        }
    }
}
