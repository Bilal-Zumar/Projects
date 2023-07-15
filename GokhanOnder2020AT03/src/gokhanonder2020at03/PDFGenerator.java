/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gokhanonder2020at03;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 *
 * @author hp
 */
public class PDFGenerator {
    File pdfFile;
    PDDocument document;
    PDFGenerator(){
        document = new PDDocument();
    }
    public void execute(List<Player> pl) throws IOException{
        generatePDFFromString(pl);
        showPDFFile();
    }
    private void generatePDFFromString(List<Player> pl) throws IOException{
        for (Player p:pl ){
            addPage(p);
        }
        document.save(System.getProperty("user.dir")+"\\src\\gokhanonder2020at03\\players_doc.pdf");
        document.close();
    }
    private void showPDFFile(){
        try {

            pdfFile = new File(System.getProperty("user.dir")+"\\src\\gokhanonder2020at03\\players_doc.pdf");
            if (pdfFile.exists()) {

                    if (Desktop.isDesktopSupported()) {
                            Desktop.getDesktop().open(pdfFile);
                    } else {
                            System.out.println("Awt Desktop is not supported!");
                    }

            } else {
                    System.out.println("File is not exists!");
            }

            System.out.println("Done");

	  } catch (Exception ex) {
		ex.printStackTrace();
	  }
        
    }
    private void addPage(Player player) throws IOException{
        PDPage myPage = new PDPage();
        document.addPage(myPage);
        try (PDPageContentStream cont = new PDPageContentStream(document, myPage)) {

                cont.beginText();

                cont.setFont(PDType1Font.TIMES_ROMAN, 12);
                cont.setLeading(14.5f);

                cont.newLineAtOffset(25, 725);
                
                cont.newLine();
                cont.newLine();
                DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
                Date dateobj = new Date(); 
                String outputText = "Player > "+player.getUserName()+", "+player.getTagName()+", "+df.format(dateobj);
                cont.showText(outputText);
                
                cont.newLine();
                cont.newLine();

                outputText="Achievements > ";
                cont.showText(outputText);
                cont.newLine();
                for(Achievement ach:player.getAchievementList()){
                    cont.newLine();
                    outputText="            "+ach.getDescription()+"  " +Integer.toString(ach.getLevel())+"  "+Integer.toString(ach.getMaximum());
                    cont.showText(outputText);
                    cont.newLine();
                }

                cont.endText();
            }
    }
}
