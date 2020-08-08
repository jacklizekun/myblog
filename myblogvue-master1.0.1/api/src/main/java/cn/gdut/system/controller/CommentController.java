package cn.gdut.system.controller;

import cn.gdut.common.constant.CommonConstant;
import cn.gdut.common.constant.SiteConstant;
import cn.gdut.common.controller.BaseController;
import cn.gdut.common.exception.GlobalException;
import cn.gdut.common.utils.IpUtil;
import cn.gdut.common.utils.QueryPage;
import cn.gdut.common.utils.R;
import cn.gdut.system.entity.SysComment;
import cn.gdut.system.service.CommentService;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * @Desctiption TODO
 * @Date 2019/11/18/018
 **/
@RestController
@RequestMapping("/api/comment")
public class CommentController extends BaseController {

    @Autowired
    CommentService commentService;

    @GetMapping("/findAll")
    public R findAll(){
        return new R<>(commentService.findAll());
    }

    @PostMapping("/list")
    public R findByPage(@RequestBody SysComment comment, QueryPage queryPage){
        return new R<>(super.getData(commentService.findByPage(comment,queryPage)));
    }

    @PostMapping
    public R add(HttpServletRequest request, @RequestBody SysComment comment){
        try {
            /**
             * 将传入进来的common添加ip设备等信息
             */
            String ip = IpUtil.getIpAddr(request);
            comment.setIp(ip);
            comment.setTime(new Date());
            comment.setAddress(null);
            // 获取headder
            String header = request.getHeader(CommonConstant.USER_AGENT);
            UserAgent userAgent = UserAgent.parseUserAgentString(header);
            // 获取浏览器
            Browser browser = userAgent.getBrowser();
            // 获取操作系统
            OperatingSystem operatingSystem = userAgent.getOperatingSystem();
            //os
            comment.setDevice(browser.getName() + "-" + operatingSystem.getName() );
            commentService.add(comment);
            return new  R<>();
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

    /**
     * 这篇文章显示的评论条数
     * @return
     */
    @GetMapping("/listForArticle")
    public R listForArticle(@RequestParam("articleId") String articleId,
                            @RequestParam("page") String page){
        if (page == null || page.equals(" ")){
            page = "1";
        }

        QueryPage queryPage = new QueryPage(Integer.valueOf(page), SiteConstant.COMMENT_PAGE_LIMIT);
        Map<String, Object> commentList = commentService.findCommentList(queryPage, articleId, SiteConstant.ARTICLE_SORT);
        return new R<>(commentList) ;

    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable String id){
        try {
            commentService.delete(id);
            return new R<>();
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }
}
