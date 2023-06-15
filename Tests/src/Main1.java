import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main1 {

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\ported\\Port_All_201802050000_1021.csv");

        FileInputStream inputStream = new FileInputStream(file);

        BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream));
        List<String> list = new ArrayList<>();

        String line;
        while ((line = bReader.readLine()) != null) {
            try {
                if (line != null && !line.trim().isEmpty()) {
                    String values[] = line.trim().split(",");
                    if (values[0].isEmpty() || values[0].equals("Number")) {
                        continue;
                    }
                    String defCode = values[0].substring(0, 4);
                    if ((defCode.equals("7700") || defCode.equals("7708")) && values[1].equals("mAltel")) {
                        continue;
                    }
                    if ((defCode.equals("7707") || defCode.equals("7747")) && values[1].equals("mMTS")) {
                        continue;
                    }

                    if (defCode.equals("7700") || defCode.equals("7708") || defCode.equals("7707") || defCode.equals("7747") || values[1].equals("mMTS") || values[1].equals("mAltel"))
                    {
                        StringBuilder dataBuilder = new StringBuilder();
                        appendFieldValue(dataBuilder, values[0]);
                        appendFieldValue(dataBuilder, values[1]);
                        appendFieldValue(dataBuilder, values[2]);
                        appendFieldValue(dataBuilder, values[3]);

                        list.add(dataBuilder.toString());
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        FileWriter writer = new FileWriter("C:\\bemobi\\sto1.csv");

        for (String sample : list) {
            writer.write(sample+"\n");
        }
        writer.close();

    }

    private static void appendFieldValue(StringBuilder dataBuilder, String fieldValue) {
        if(fieldValue != null) {
            dataBuilder.append(fieldValue).append(",");
        } else {
            dataBuilder.append("").append(",");
        }
    }

}
