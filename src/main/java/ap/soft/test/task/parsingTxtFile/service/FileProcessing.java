package ap.soft.test.task.parsingTxtFile.service;

import ap.soft.test.task.parsingTxtFile.model.Node;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
public class FileProcessing {

    public Node  parsingTxtFile(MultipartFile file){
//        byte[] bytes;
//        try {
//            bytes = file.getBytes();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        int count = 0;
//        int depth;
//        while (count < bytes.length -1){
//           if (bytes[count] == 10 && bytes[count+1] == '#'){
//               depth = getDepth(count+1, bytes);
//               new Node(depth)
//               count+=depth;
//           }
//        }

        return null;
    }

    private int getDepth(int index, byte[] bytes){
        int result = 0;
        for (int i = index; i < bytes.length; i++){
            if (bytes[i] == '#') ++result;
        }
        return result;
    }
}
