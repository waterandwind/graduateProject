package com.restaurant.controller;


import com.restaurant.config.UuidUtils;
import com.restaurant.entity.Commodity;
import com.restaurant.entity.result.Response;
import com.restaurant.service.ICommodityService;
import com.sun.deploy.net.URLEncoder;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.jni.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zyw
 * @since 2019-12-23
 */
@RestController
@RequestMapping("/commodity")
public class CommodityController {
    private static final String FILE_PATH=System.getProperty("user.dir")+ "/src/main/resources/profile/";
    @Autowired
    ICommodityService iCommodeytService;


    @PostMapping("saveCommodity")
    @ApiOperation(value = "保存商品")
    public boolean saveCommodity(@RequestBody(required=true) Commodity commodity)  throws Exception{
        return  iCommodeytService.save(commodity);
    }

    @PostMapping("/upload")
    @ResponseBody
    public Response upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Response.bizError("上传失败，请选择文件");
        }

        String fileName = UuidUtils.reName(file.getOriginalFilename());

        File dest = new File(FILE_PATH );
        if(!dest.exists()){
            dest.mkdir();
        }
        try {
            dest = new File(FILE_PATH+fileName);
            file.transferTo(dest);
            return Response.success("上传成功",fileName);
        } catch (IOException e) {
          return Response.bizError("未知异常");
        }
    }
    @GetMapping(value = "/imgShow/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Resource> imgShow(@PathVariable("fileName") String fileName) throws FileNotFoundException {
        InputStream inputStream=new FileInputStream(new File(FILE_PATH+fileName));
        InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(inputStreamResource,headers, HttpStatus.OK);
    }
    @PostMapping
    public Response createCommodity(Commodity commodity) {
         boolean rs = iCommodeytService.save(commodity);
         if (rs){
            return Response.success("保存成功");
         }else {
           return Response.bizError("保存失败");
         }
    }

}

