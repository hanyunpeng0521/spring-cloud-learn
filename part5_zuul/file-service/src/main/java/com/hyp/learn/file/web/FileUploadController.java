package com.hyp.learn.file.web;

import io.swagger.annotations.Api;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * @author hyp
 * Project name is spring-cloud-learn
 * Include in com.hyp.learn.us.web
 * hyp create at 20-1-27
 **/
@Api(tags = "文件操作")
@RestController
@RequestMapping("/file")
public class FileUploadController {
    @PostMapping(value = "/upload")
    public @ResponseBody
    String handleFileUpload(@RequestParam(value = "file",
            required = true) MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        File fileToSave = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileCopyUtils.copy(bytes, fileToSave);

        return fileToSave.getAbsolutePath();
    }
}
