package ap.soft.test.task.parsingTxtFile.controller;

import ap.soft.test.task.parsingTxtFile.model.ClientType;
import ap.soft.test.task.parsingTxtFile.model.FileStructure;
import ap.soft.test.task.parsingTxtFile.model.Node;
import ap.soft.test.task.parsingTxtFile.service.HandlerTextFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class SimpleController {

    private final HandlerTextFile fileProcessing;

    public SimpleController(HandlerTextFile fileProcessing) {
        this.fileProcessing = fileProcessing;

    }

    @GetMapping("/main")
    public String mainA(){
        return "main";
    }

    @PostMapping("/main")
    public String main(@RequestParam("txt_file") MultipartFile file, Model model){
        FileStructure fileStructure = null;
        if (!file.isEmpty()) {
            try {
                fileStructure = fileProcessing.parsingTxtFile(file, ClientType.HTML_CLIENT);
            } catch (Exception e) {
                e.printStackTrace();
            }
            model.addAttribute("strings", fileStructure.getStrings());
            Node firstNode = fileStructure.getFirstNode();
            model.addAttribute("nodes", print(firstNode));
        }
        return "main";
    }

    public String print(Node node){
        StringBuilder sb = new StringBuilder();
        print(node, 0, sb);
        return sb.toString();
    }

    private void print(Node<?> node, int countSpace, StringBuilder result) {
        for (int i = 0; i < countSpace; i++) {
            result.append("_____");
        }
        result.append(node.getLineID()).append("_(Depth = ").append(node.getDepth()).append(" )<br/>");
        if (!node.getChildren().isEmpty()) {
            for (Node n : node.getChildren()) {
                print(n, countSpace + 1, result);
            }
        } else return;
    }


}
