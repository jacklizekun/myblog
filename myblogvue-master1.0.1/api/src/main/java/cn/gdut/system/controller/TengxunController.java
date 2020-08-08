package cn.gdut.system.controller;

import cn.gdut.common.exception.GlobalException;
import cn.gdut.common.properties.BlogProperties;
import cn.gdut.common.properties.TengxunProperties;
import cn.gdut.common.utils.R;
import cn.gdut.system.entity.TengxunEntity;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

/**
 * @Desctiption 腾讯云对象存储
 * @Date 2019/11/26/026
 **/
@RestController
@RequestMapping("/api/storage/qiniu")
public class TengxunController {

    @Autowired
    private BlogProperties properties;

    /**
     * 生成cos客户端
     * @return
     */
    private COSClient createCOSClient(){
        TengxunProperties tengxun = properties.getTengxun();
        // 初始化身份信息
        BasicCOSCredentials cred = new BasicCOSCredentials(tengxun.getAk(),tengxun.getSk());
        //设置地区
        Region region = new Region(tengxun.getBucket());
        // 设置clientConfig
        ClientConfig clientConfig = new ClientConfig(region);
        // 生成cos客户端
        COSClient client = new COSClient(cred, clientConfig);
        return client;
    }


    /**
     * 获取空间文件列表
     * @return
     */
    @GetMapping("/list")
    public R list(){
        try {
            TengxunProperties tengxun = properties.getTengxun();
            ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
            // 设置桶名称
            listObjectsRequest.setBucketName(tengxun.getBn());
            // prefix表示列出Object的key以prefix开始
            listObjectsRequest.setPrefix("");
            // 设置最大遍历出多少个对象。一次listObject最大支持1000
            listObjectsRequest.setMaxKeys(1000);
//            listObjectsRequest.setDelimiter("/");
            COSClient cosClient = createCOSClient();
            ObjectListing objectListing = cosClient.listObjects(listObjectsRequest);

            // 用于存储实体
            List<TengxunEntity> list = new ArrayList<>();
            // 遍历
            for (COSObjectSummary cosObjectSummary : objectListing.getObjectSummaries()){
                // 赋值属性etag，name，type，url
                TengxunEntity tengxunEntity = new TengxunEntity(cosObjectSummary.getETag(), cosObjectSummary.getKey(), "", cosObjectSummary.getSize(), tengxun.getUrl().trim() + cosObjectSummary.getKey());
                list.add(tengxunEntity);
            }
            Map<String , Object> map = new HashMap<>();
            map.put("rows", list);
            return new R<>(map);
        }
        catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

    /**
     * 上传文件
     * 接口文档https://cloud.tencent.com/document/product/436/10199#.E4.B8.8A.E4.BC.A0.E5.AF.B9.E8.B1.A1
     * @param file 文件
     * @param request request
     * @return
     */
    @RequestMapping("/upload")
    public R upload(@RequestParam("file") MultipartFile file, HttpServletRequest request){

        TengxunProperties tengxun = properties.getTengxun();
        if (!file.isEmpty()){
            /**先将文件存到本地，再上传上去
             *
             */
            // 上传到服务器的路径
            String TengXunPath = "";
            // 保存后的文件名
            String key = new Date().getTime() + "";
            try {
                // 将MultipartFile转化为File类型，相当于需要以本地作为缓冲区存储文件
                // 获取文件在服务器的存储位置
                File path = new File(ResourceUtils.getURL("classpath:").getPath());
                // 相当于添加子文件夹
                File filePath = new File(path.getAbsolutePath(), "upload/");
                if (filePath.exists() && !filePath.isDirectory()){
                    // 创建文件夹
                    filePath.mkdir();
                }
                // 获取文件名称
                String originalFilename = file.getOriginalFilename();
                // 获取文件的类型 .jpg
                String substringName = originalFilename.substring(originalFilename.lastIndexOf("."));
                // 完整的名称
                key += substringName;
                // 本地的文件路径
                File local = new File(filePath, key);
                // 写入磁盘中
                file.transferTo(local);
                System.out.println("新文件的路径"+ filePath);
                System.out.println("新文件的名称"+key);


                TengXunPath = key;

                // 上传到服务器
                PutObjectRequest putObjectRequest = new PutObjectRequest(tengxun.getBn(), TengXunPath, local);
                COSClient cosClient = createCOSClient();
                PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
                System.out.println(putObjectResult);
                System.out.println("文件上传成功===========>"+putObjectResult.getMetadata());
                Map<String, Object> map = new HashMap<>();
                map.put("name", key);
                map.put("url", tengxun.getUrl() + key);
                return new R<>(map);

            }catch (Exception e){
                throw new GlobalException(e.getMessage());
            }
        }
        // 否则，上传文件为空
        return new R <>(400,"上传文件错误");
    }
}
