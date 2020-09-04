package ap.soft.test.task.parsingTxtFile.controller;

import ap.soft.test.task.parsingTxtFile.model.ClientType;
import ap.soft.test.task.parsingTxtFile.model.FileStructure;
import ap.soft.test.task.parsingTxtFile.model.Node;


import ap.soft.test.task.parsingTxtFile.service.HandlerTextFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class SimpleController {

    public static final Logger logger = LoggerFactory.getLogger(SimpleController.class);

    private final HandlerTextFile fileProcessing;

    public SimpleController(HandlerTextFile fileProcessing) {
        this.fileProcessing = fileProcessing;

    }

    @GetMapping("/main")
    public String mainA(){
        logger.debug("HTML page \"view.html\" sent to user");
        return "view";
    }

    @PostMapping("/main")
    public String main(@RequestParam("txt_file") MultipartFile file, Model model){
        logger.info("@PostMapping(/main) with parameter MultipartFile. "
                    + (file != null ? "File.size = " +  file.getSize() + " bytes," + " File.name = " + file.getOriginalFilename() : "null"));
        FileStructure fileStructure = null;
        if (file != null && !file.isEmpty()) {
            logger.debug("File processing to start");
            try {
                fileStructure = fileProcessing.parsingTxtFile(file, ClientType.HTML_CLIENT);
            } catch (Exception e) {
                logger.warn("file could not be processed", e);
                model.addAttribute("errorMessage", "File is not read");
            }
            logger.info("Strings and Nodes adding to model");
            model.addAttribute("strings", fileStructure.getStrings());
            logger.debug("Strings added to model");
            Node firstNode = fileStructure.getFirstNode();
            model.addAttribute("nodes", "<ul class=\"treeCSS\">" + print(firstNode) + "</ul>");
            logger.info("Strings and Nodes added to model");
        } else {
            logger.debug("Error message \"File is empty\" sent to user");
            model.addAttribute("errorMessage", "File is empty");
        }
        logger.debug("HTML page \"view.html\" sent to user");
        return "view";
    }

    public String print(Node node){
        StringBuilder sb = new StringBuilder();
        print(node, 0, sb);
        return sb.toString();
    }

    private void print(Node<?> node, int countSpace, StringBuilder result) {
        if (!node.getChildren().isEmpty()) {
            result.append("<li>").append(node.getLineID()).append("_(Depth = ").append(node.getDepth()).append(" )<ul>");
            for (Node n : node.getChildren()) {
                print(n, countSpace + 1, result);
            }
            result.append("</ul>");
        } else {
            result.append("<li>").append(node.getLineID()).append("_(Depth = ").append(node.getDepth());
            return;
        }
    }

}
