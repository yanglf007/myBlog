package com.yanglf.usermanage.controller;

import com.yanglf.usermanage.utils.FTPUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/pic")
public class PicController {


    @Value("${blogPicPath}")
    private String blogPicPath;
    @RequestMapping("/editormdPic")

    public @ResponseBody Map editormdPic (@RequestParam(value = "editormd-image-file", required = true) MultipartFile file,
                                          HttpServletRequest request) throws Exception{

        String trueFileName = file.getOriginalFilename();

        String suffix = trueFileName.substring(trueFileName.lastIndexOf("."));


        String fileName = UUID.randomUUID().toString().replaceAll("-", "")+suffix;

        FTPUtils.upload(file.getInputStream(), fileName,blogPicPath);

        //保存

        Map resultMap = new HashMap();
        resultMap.put("success", 1);
        resultMap.put("message", "上传成功！");
        resultMap.put("url","http://132.232.14.175/blog/"+fileName);

        JSONObject json = new JSONObject();
        json.put("success", 1);
        json.put("message", "上传成功！");
        json.put("url","http://132.232.14.175/blog/"+fileName);
        request.getSession().setAttribute("json", json.toString());
        return resultMap;

    }
}
