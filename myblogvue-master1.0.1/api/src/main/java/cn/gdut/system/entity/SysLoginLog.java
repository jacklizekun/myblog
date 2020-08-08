package cn.gdut.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Desctiption TODO
 * @Date 2019/11/26/026
 **/
@ToString
@TableName(value = "tb_login_log")
@Data
public class SysLoginLog  implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField("username")
    private String userName;

    private String location;


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("create_time")
    private Date createTime;

    private String device;

    private String ip;

}
