package cn.gdut.system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Desctiption TODO
 * @Date 2019/11/26/026
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TengxunEntity {

    private String key;
    private String name;
    private String type;
    private long size;
    private String url;
}
