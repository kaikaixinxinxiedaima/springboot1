package com.test.controller;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

@Controller
public class XmlReturnTest {
    private static ResourceLoader resourceLoader = new DefaultResourceLoader();


    @RequestMapping(value = "/readXml", produces = "application/xml;charset=utf-8")
    @ResponseBody
    public String readXml(){
        BufferedInputStream bis = null;
        String str = null;
        Resource resource = resourceLoader.getResource("config/generatorConfig.xml");
        int  fileSize = 0;
        try {
            fileSize = (int) resource.getFile().length();
            bis = new BufferedInputStream(resource.getInputStream());
            byte[] buf = new byte[fileSize];
            int length = 0;
            if((length = bis.read(buf))!=-1){
                str = new String(buf);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;

    }

}
