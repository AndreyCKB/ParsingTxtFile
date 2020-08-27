package ap.soft.test.task.parsingTxtFile.service;

import ap.soft.test.task.parsingTxtFile.model.ClientType;
import ap.soft.test.task.parsingTxtFile.model.FileStructure;
import ap.soft.test.task.parsingTxtFile.model.Node;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HandlerTextFileImplTest {
    private final HandlerTextFile handlerTextFile = new HandlerTextFileImpl();

    @Test
    void parsingTxtFile() throws URISyntaxException, IOException {
        File file = new File("src/test/resources/test.txt");
        assertTrue(Files.exists(Paths.get(file.getAbsolutePath())));

        List<String> strings = new ArrayList<>();
        int countNodeFile = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            while (bufferedReader.ready()){
                String s = bufferedReader.readLine();
                strings.add(s);
                if(!s.isEmpty() && s.toCharArray()[0] == '#'){
                    countNodeFile++;
                }
            }
        }

        MultipartFile multipartFile = new MockMultipartFile("test.xlsx", new FileInputStream(file));
        FileStructure fileStructure = this.handlerTextFile.parsingTxtFile(multipartFile, ClientType.ARRAY_CLIENT);
        comparingLinesInFileAndFileStructure(strings, fileStructure.getStrings());
        comparingTheNumberOfLinesInFileAndFileStructure(countNodeFile, getCountNode(fileStructure.getFirstNode(),0) -1);


    }

    private void comparingLinesInFileAndFileStructure(List<String> fileLines, List<String> fileStructureLines){
        assertTrue(fileLines.containsAll(fileStructureLines));
    }

    private void comparingTheNumberOfLinesInFileAndFileStructure(int countNodeFile, int countNodeFileStructure){
        assertTrue(countNodeFile == countNodeFileStructure);
    }

    private int getCountNode(Node node,int countNode){
        countNode++;
        if (!node.getChildren().isEmpty()) {
            List<Node> children = node.getChildren();
            for (Node n : children) {
               countNode = getCountNode(n, countNode);
            }
        }
        return countNode;
    }
}