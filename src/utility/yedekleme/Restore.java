package utility.yedekleme;

import java.io.IOException;

public class Restore extends Base {


    public Process restoreIt(String fileAndPath) {
        ProcessBuilder processBuilder = new ProcessBuilder(commandPath + "pg_restore", "-h", IP, "-U", user, "-d", dbase, "-v", "-c", fileAndPath);
        processBuilder.environment().put("PGPASSWORD", password);
        processBuilder.redirectErrorStream(true);
        try {
            return processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
