package cn.gdut.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Desctiption TODO
 * @Date 2019/11/18/018
 **/
@Data
@TableName("tb_comment")
public class SysComment {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long pId;
    private Long cId;
    private String articleTitle;
    private Long articleId;
    private String name;
    private String cName;

    //    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;
    private String content;
    private String email;
    private String url;
    private Integer sort;
    private String ip;
    private String device;
    private String address;


}