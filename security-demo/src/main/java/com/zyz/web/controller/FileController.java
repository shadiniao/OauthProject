package com.zyz.web.controller;

import com.zyz.dto.FileInfo;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 2018/8/31.
 *
 * @author zhangyizhi
 */
@RestController
@RequestMapping("/file")
public class FileController {

    String folder = "E:\\developer\\idea_project\\OauthProject\\security-demo\\src\\main\\resources\\upload";

    // 参数file的名称要与测试用例中MockMultipartFile的name一致
    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {
        String message = "name:" + file.getName()
                + ",originalFilename:" + file.getOriginalFilename()
                + ",size:" + file.getSize()
                + ",content type:" + file.getContentType();

        System.out.println(message);


        File localFile = new File(folder, new Date().getTime() + ".txt");

        file.transferTo(localFile);

        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (InputStream inputStream = new FileInputStream(new File(folder, id + ".txt"));
             OutputStream outputStream = response.getOutputStream();) {
            response.setContentType("application/x-download"); // 下载要设置contentType
            response.addHeader("Content-Disposition", "attachment;filename=test.txt"); // 通过响应头设置下载的文件名

            IOUtils.copy(inputStream, outputStream);

            outputStream.flush();
        }
    }
}
