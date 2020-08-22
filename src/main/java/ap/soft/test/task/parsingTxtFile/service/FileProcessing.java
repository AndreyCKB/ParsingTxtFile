package ap.soft.test.task.parsingTxtFile.service;

import ap.soft.test.task.parsingTxtFile.model.DataProcessedFromTextFile;
import ap.soft.test.task.parsingTxtFile.model.Node;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


@Service
public class FileProcessing {

    public DataProcessedFromTextFile  parsingTxtFile(MultipartFile file){
        DataProcessedFromTextFile result = new DataProcessedFromTextFile( Node.firsNode("<a href=\"#startDocument\">" + "start</a>"),new ArrayList<String>());
        int countNode = 0;
        String nameNode;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))){
            String currentStr;

            if(reader.ready()){
                currentStr = reader.readLine();
                if (currentStr.isEmpty() || currentStr.toCharArray()[0] != '#'){
                    result.addString("<a name=\"startDocument\">" + currentStr +"</a>");
                } else {
                    result.addString("<a name=\"startDocument\"></a>"+  "<a name=\"" + (++countNode) + "\"></a>" + currentStr);
                    result.addNode("<a href=\"#" + countNode + "\">" + countNode +"</a>" , getDepth(currentStr.toCharArray()));
                }
            }
            while (reader.ready()){
                currentStr = reader.readLine();
                if (currentStr.isEmpty() || currentStr.toCharArray()[0] != '#') {
                    result.addString(currentStr);
                }else {
                   result.addString("<a name=\"" + (++countNode) + "\"></a>" + currentStr);
                   result.addNode("<a href=\"#" + countNode + "\">" + countNode + "</a>" , getDepth(currentStr.toCharArray()));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result.toString());
        return result;
    }

    private int getDepth(char[] chars){
        int result = 0;
        while (chars[result] == '#'){
            ++result;
        }
        return result;
    }
}
