package cn.gdut.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Desctiption TODO
 * @Date 2019/11/18/018
 **/
@Data
@TableName("tb_article")
public class SysArticle implements Serializable {

    // 自增
    @TableId(value = "id" ,type = IdType.AUTO)
    private Long id;
    private String title;
    private String cover;
    private String author;
    private String content;

    private String contentMd;
    private String category;
    private String state;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("publish_time")
    private Date publishTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("edit_time")
    private Date editTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("create_time")
    private Date createTime;

    // 是否为数据库表字段
    @TableField(exist = false)
    private List<SysTag> tags;

    public SysArticle(){}

    public SysArticle(Long id, String category){
        this.id = id;
        this.category =category;
    }

    public SysArticle(String title, String category){
        this.title = title;
        this.category = category;
    }
}

