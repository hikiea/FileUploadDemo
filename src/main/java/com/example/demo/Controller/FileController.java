package com.example.demo.Controller;

import com.example.demo.Mapping.AddHeadAddress;
import com.example.demo.Model.Head;
import com.example.demo.Tool.FileUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Controller
public class FileController {

    @Autowired
    private AddHeadAddress addHeadAddress;

    //跳转到上传文件的页面
    @GetMapping("/")
    public String goUploadImg(){
        return "index";
    }

    //处理上传的文件
    @PostMapping(value = "/doUpload")
    public String uploadImg(
            // 定义一个 MultipartFile 类型的参数 file
            @RequestParam("file") MultipartFile file,
            Model model){
        // 如果图片为空，显示error
        if ((file.getOriginalFilename().isEmpty() )) {
            model.addAttribute("error","error");
            return "index";
        }else{
            // 1. 创建一个 head 对象
            Head head = new Head();
            // 2. 获取图片的名字
            String fileName = file.getOriginalFilename();

            // 3. 防止图片名字相同而出现bug，使用 uuid 加密
            String hToken = UUID.randomUUID().toString();

            // 4. 加密后的图片名字：uuid + 图片名字
            String HeadName = hToken + fileName;

            // 5. 图片存放的文件夹地址
            String filePath = "C:\\Users\\Lzy\\Desktop\\FileUploadDemo\\src\\main\\resources\\static\\picture\\";
            // 6.  图片的路径 = 文件夹地址 + 名字
            String fileAddress = filePath + HeadName;
            try{
                // 图片上传的工具类
                // 参数一：图片的编码格式的字节数组
                // 参数二：图片存放的文件夹地址
                // 参数三：图片的名字（加密后）
                FileUtil.uploadFile(file.getBytes(),filePath,HeadName);

                // 把图片路径,图片名写入数据库
                head.setHeadAddress(fileAddress);
                head.setHeadName(HeadName);
                addHeadAddress.insert(head);

            } catch (Exception e){
            }
            return "success";
        }
    }

    @GetMapping("/showHead")
    public String toShow(){
        return "success";
    }

    @PostMapping(value = "/showHead")
    public String showHead(
            @RequestParam("id") Integer id,
            Model model
    ){
        List<Head> byId = addHeadAddress.getById(id);
        model.addAttribute("byId",byId);
        return "success";
    }

}
