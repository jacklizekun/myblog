package cn.gdut;

import cn.gdut.common.utils.QueryPage;
import cn.gdut.system.entity.SysComment;
import cn.gdut.system.mapper.CommentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Desctiption TODO
 * @Date 2019/11/18/018
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class Comment {

    @Autowired
    CommentMapper commentMapper;

    @Test
    public void findAll(){

        QueryPage queryPage = new QueryPage(1,8);
        List<SysComment> comments = commentMapper.findAll("1", queryPage);
        for (SysComment comment : comments){
            System.out.println(comment);
        }
    }
}
