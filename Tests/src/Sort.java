import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sort {

    public static void main(String[] args) throws IOException {
        List<String> newFiles = listFilesForFolder(new File("C:\\ported"));

        Collections.sort(newFiles, (o1, o2) -> {
            String s1 = o1.replace("Port_Increment", "")
                    .replace("Return_Increment", "")
                    .substring(0, 13);
            String s2 = o2.replace("Port_Increment", "")
                    .replace("Return_Increment", "")
                    .substring(0, 13);
            System.out.println(s1+" "+s2);
            int value = s1.compareTo(s2);
            if (value == 0) {
                System.out.println("equal"+s1+" "+s2+" "+o1.contains("Port_Increment"));

                return o1.contains("Port_Increment") ? -1 : 1;
            }
            return value;
        });
        System.out.println(newFiles);
    }

    public static List<String> listFilesForFolder(final File folder) {

        List<String> newFiles = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                if (fileEntry.getName().contains("Port_Increment") || fileEntry.getName().contains("Return_Increment"))
                newFiles.add(fileEntry.getName());
            }
        }
        System.out.println(newFiles);
        return newFiles;
    }
}
