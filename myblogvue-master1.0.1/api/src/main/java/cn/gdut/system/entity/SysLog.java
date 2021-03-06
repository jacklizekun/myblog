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

/**
 * @Desctiption TODO
 * @Date 2019/11/26/026
 **/
@TableName(value = "tb_log")
@Data
public class SysLog implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;
    private String operation;
    // 操作时间
    private Long time;
    private String method;
    private String params;
    private String ip;
    @TableField(value = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createTime;
    private String location;
}
