package safetydetector;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class KeywordFileHandler {
    public static final String HARMFUL="harmfulkeywords.txt";
    public static final String SCAM="scamkeywords.txt";
    public static final String HARASSMENT="harassmentkeywords.txt";
    public static final String HARASSMENT_PATTERN="harassmentpatterns.txt";
    public static final String SCAM_PATTERN="scampatterns.txt";
    public static final String HARMFUL_PATTERN="harmfulpatterns.txt";
    public static void write(String file, String line)
    {
        try(BufferedWriter bw=new BufferedWriter(new FileWriter(file,true)))
        {
            bw.write(line);
            bw.newLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public static List<String> read(String file) {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return list;
    }


}

