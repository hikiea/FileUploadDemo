package com.example.demo.Controller;

import com.example.demo.Mapping.AddHeadAddress;
import com.example.demo.Model.Head;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FileShowApiController {

    @Autowired
    private AddHeadAddress addHeadAddress;

    @GetMapping("head/showAllFileApi")
    @ResponseBody
    public List<Head> showAllFileApi(){
        List<Head> all = addHeadAddress.getAll();
        return all;
    }

    @GetMapping("head/showFileApi/{id}")
    @ResponseBody
    public List<Head> showFileApiById(@PathVariable Integer id){
        List<Head> date = addHeadAddress.getById(id);
        return date;
    }

    @PostMapping("/post")
    public String addFile(@RequestBody Head head){
        addHeadAddress.insert(head);
        return "index" ;
    }

}
