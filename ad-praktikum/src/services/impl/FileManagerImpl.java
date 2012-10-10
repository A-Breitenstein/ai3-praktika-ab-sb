package services.impl;

import praktiukum1.MonitorRecord;
import services.service.FileManager;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * AD-Praktikum
 * Team: 13
 * Date: 03.10.12
 * Time: 20:03
 */
public class FileManagerImpl implements FileManager {

    @Override
    public int[] fetchFolgeFromFile(String path) {
        int[] folge;
        final InputStream inputStream;
        BufferedReader br;
        String line ="";
        List<String> list = new ArrayList<String>();
        try{
            inputStream = new FileInputStream(path);
            br = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            while ((line = br.readLine()) != null) {
                list.addAll(Arrays.asList(line.split(" ")));
            }
        }catch(IOException exc){
            Logger.getGlobal().log(Level.OFF, line, exc);
        }
        folge = new int[list.size()];
        int counter = 0;
        for (String string : list) {
            folge[counter++] = Integer.decode(string);
        }
        return folge;
    }

    @Override
    public void exportCSV(Map<String, MonitorRecord> recordMap, int iterations) {
        try {
            Date d = new Date();
            File exportFile = new File("Log" + d.getYear() + "" + d.getMonth() + "" + d.getDay() + "-ADP-" + d.getHours() + "-" + d.getMinutes() +"-" +d.getSeconds() +"_"+iterations+"-iterations" +".csv");
            FileWriter fout;

            fout = new FileWriter(exportFile);

            fout.write(MonitorRecord.CSVFILEHEAD_SD + "\n");
            List<MonitorRecord> list = new ArrayList<MonitorRecord>(recordMap.values());
            Collections.sort(list);
            for (MonitorRecord mr : list)
                fout.write(mr.toString() + "\n");

            fout.flush();

            fout.close();
        } catch (IOException ex) {
            Logger.getLogger(FileManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void exportTimelineCSV(Map<String, MonitorRecord> recordMap, int iterations) {
        try {
            Date d = new Date();
            File exportFile = new File("Timeline" + d.getYear() + "" + d.getMonth() + "" + d.getDay() + "-ADP-" + d.getHours() + "-" + d.getMinutes() +"-" +d.getSeconds() +"_"+iterations+"-iterations" +".csv");
            FileWriter fout;

            fout = new FileWriter(exportFile);

            fout.write(MonitorRecord.CSVFILEHEAD_TL + "\n");
            List<MonitorRecord> list = new ArrayList<MonitorRecord>(recordMap.values());
            Collections.sort(list);
            for (MonitorRecord mr : list)
                fout.write(mr.getTimeLineCVS() + "\n");

            fout.flush();

            fout.close();
        } catch (IOException ex) {
            Logger.getLogger(FileManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void exportRandomSequence(final int[] array) {
        try {
            Date d = new Date();
            File exportFile = new File("RandomSequence" + d.getYear() + "" + d.getMonth() + "" + d.getDay() + "-ADP-" + d.getHours() + "-" + d.getMinutes() +"-" +d.getSeconds() +"_"+array.length+"-length" +".rnd");
            FileWriter fout;

            fout = new FileWriter(exportFile);



            for (int i = 0; i < array.length; i++) {
                fout.write(String.valueOf(array[i])+"\n");
            }

            fout.flush();

            fout.close();
        } catch (IOException ex) {
            Logger.getLogger(FileManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
