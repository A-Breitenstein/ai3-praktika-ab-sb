import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sven
 * Date: 25.10.12
 * Time: 20:06
 * To change this template use File | Settings | File Templates.
 */
public class GKAFileManager {
    public static List<List<String>> importGKAFile(String path){
        final InputStream inputStream;
        BufferedReader br;
        String line ="";
        List<List<String>> list = new ArrayList<List<String>>();
        try{
            inputStream = new FileInputStream(path);
            br = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String fileHead = br.readLine();
            if(fileHead.equals("#ungerichtet") ||fileHead.equals("#gerichtet") ){
                //sieht komisch aus, aber graphPropertys.add(0,fileHead) wirft sonst
                // Exception in thread "main" java.lang.UnsupportedOperationException
                // at java.util.AbstractList.add(AbstractList.java:148)
                List<String> graphPropertys = new ArrayList<String>();
                graphPropertys.addAll(Arrays.asList(br.readLine().split(",")));

                if(graphPropertys.contains("#gewichted") || graphPropertys.contains("#attributiert")){
                   graphPropertys.add(0, fileHead);
                   list.add(graphPropertys);
                }else{
                    list.add(Arrays.asList(fileHead));
                    list.add(graphPropertys);
                }

                while ((line = br.readLine()) != null) {
                    list.add(Arrays.asList(line.split(",")));
                }

            }else {
                br.close();
                throw new IOException("GKA-FileHead does not match");
            }
        }catch(IOException exc){
            exc.printStackTrace();
        }
        return list;

    }

    public static void main(String[] args) {
        System.out.println(importGKAFile("graph1.gka"));
        System.out.println(importGKAFile("graph2.gka"));
        System.out.println(importGKAFile("graph3.gka"));
        System.out.println(importGKAFile("graph4.gka"));
    }
}
