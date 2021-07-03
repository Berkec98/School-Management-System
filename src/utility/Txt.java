package utility;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Txt {
    private String path;

    public Txt(String path) {
        this.path = path;
    }

    public void toTXT(String toWrite) throws IOException {
        File f = new File(path);
        Writer wr = new FileWriter(f);
        wr.write(toWrite);
        wr.flush();
        wr.close();
    }



    public String fromTXT() throws FileNotFoundException {
        Scanner sc = new Scanner(new File(path));
        return sc.hasNextLine() ? sc.nextLine() : "";
    }


    /**
     * TXT yi okur satırları listeye doldurarak return eder
     * @return txt dosyasının içeriğini string list olarak return eder
     * @throws IOException dosya bulunamazsa bu hata meydana gelir
     */
    public List<String> readAllFromFileToList() throws IOException {
        List<String> list = new ArrayList<>();
        String line;
        BufferedReader br = new BufferedReader(new FileReader(new File(path)));
        while ((line = br.readLine()) != null) {
            list.add(line);
        }
        br.close();
        return list;
    }
}
