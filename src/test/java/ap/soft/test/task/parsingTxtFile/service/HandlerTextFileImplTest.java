package ap.soft.test.task.parsingTxtFile.service;

import ap.soft.test.task.parsingTxtFile.model.ClientType;
import ap.soft.test.task.parsingTxtFile.model.FileStructure;
import ap.soft.test.task.parsingTxtFile.model.Node;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.event.annotation.BeforeTestClass;
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

    private final HandlerTextFile handlerTextFile = new HandlerTextFileImpl(new FileStructureFactoryImpl('#',100));
    private static List<String> lineFromFile;
    private static MultipartFile multipartFile;
    private static int countSignSection = 0;

    @BeforeAll
    static void initFields() throws IOException {
        initMultipartFile();
        initLineFromFile();
    }

    static private void initMultipartFile() throws IOException {
        File file = new File("src/test/resources/test.txt");
        assertTrue(Files.exists(Paths.get(file.getAbsolutePath())));
        multipartFile = new MockMultipartFile("mtest.txt", new FileInputStream(file));
    }

    static private void initLineFromFile() throws IOException {
        lineFromFile = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()))) {
            while (bufferedReader.ready()){
                String s = bufferedReader.readLine();
                lineFromFile.add(s);
                if(!s.isEmpty() && s.toCharArray()[0] == '#'){
                    countSignSection++;
                }
            }
        }
    }



    @Test
    void parsingTxtFileForArrayClient() throws IOException {
        FileStructure fileStructure = this.handlerTextFile.parsingTxtFile(this.multipartFile, ClientType.ARRAY_CLIENT);
        comparingLinesInFileAndFileStructure(lineFromFile, fileStructure.getStrings());
        comparingTheNumberOfLinesInFileAndFileStructure(countSignSection, getCountNode(fileStructure.getFirstNode(),0) -1);
    }

    @Test
    void parsingTxtFileForHTMLClient() throws IOException {
        FileStructure fileStructure = this.handlerTextFile.parsingTxtFile(this.multipartFile, ClientType.HTML_CLIENT);
        assertTrue(fileStructure.getStrings().size() == lineFromFile.size());
        comparingTheNumberOfLinesInFileAndFileStructure(countSignSection, getCountNode(fileStructure.getFirstNode(),0) -1);
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