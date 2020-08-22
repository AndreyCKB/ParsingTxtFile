package ap.soft.test.task.parsingTxtFile.controller;

import ap.soft.test.task.parsingTxtFile.model.DataProcessedFromTextFile;
import ap.soft.test.task.parsingTxtFile.model.Node;
import ap.soft.test.task.parsingTxtFile.service.FileProcessing;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class SimpleController {

    private final FileProcessing fileProcessing;

    public SimpleController(FileProcessing fileProcessing) {
        this.fileProcessing = fileProcessing;
    }

    @GetMapping("/main")
    public String mainA(){
        return "main";
    }

    @PostMapping("/main")
    public String main(@RequestParam("txt_file") MultipartFile file, Model model){
        DataProcessedFromTextFile dataProcessedFromTextFile = null;
        if (!file.isEmpty()) {
            try {
                dataProcessedFromTextFile = fileProcessing.parsingTxtFile(file);
            } catch (Exception e) {
            }
            model.addAttribute("strings", dataProcessedFromTextFile.getStrings());
            Node firstNode = dataProcessedFromTextFile.getFirstNode();

            model.addAttribute("nodes", firstNode.print(firstNode));
        }
        return "main";
    }


}
