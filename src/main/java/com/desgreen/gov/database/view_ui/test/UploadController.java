package com.desgreen.gov.database.view_ui.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Controller
public class UploadController {

    //Save the uploaded file to this folder
    // private static String UPLOADED_FOLDER = "F://temp//";
    private static String UPLOADED_FOLDER = "/Users/yhawin/gambarnya/";
    // private static String UPLOADED_FOLDER = "/gambarnya/";

    @GetMapping("/test")
    public String index() {
        return "upload";
    }

    @PostMapping("/uploadSave") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file_1") MultipartFile file_1, @RequestParam("file_2") MultipartFile file_2,
                                   RedirectAttributes redirectAttributes) {



                                    
        if (file_1.isEmpty() && file_2.isEmpty() ) {
            redirectAttributes.addFlashAttribute("message1", "Please select a file to upload");
            return "redirect:test";
        }

        redirectAttributes.addFlashAttribute("message1",
        "You Fail Upload to uploaded '" + UPLOADED_FOLDER + "'");

        try {

            // Get the file and save it somewhere
            byte[] bytes = file_1.getBytes();
            // Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Path path = Paths.get(UPLOADED_FOLDER + LocalDateTime.now().getYear() +    file_1.getOriginalFilename());
            Files.write(path, bytes);

            // redirectAttributes.addFlashAttribute("message1",
                    // "You successfully uploaded '" + file_2.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {

            // Get the file and save it somewhere
            byte[] bytes = file_2.getBytes();
            // Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Path path = Paths.get(UPLOADED_FOLDER + LocalDateTime.now().getYear() +    file_2.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message2",
                    "You successfully uploaded '" + file_2.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/test";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "test";
    }

}