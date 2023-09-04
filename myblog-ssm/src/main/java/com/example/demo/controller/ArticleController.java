package com.example.demo.controller;


import com.example.demo.common.AjaxResult;
import com.example.demo.common.ApplicationVariable;
import com.example.demo.common.StringTools;
import com.example.demo.common.UserSessionTools;
import com.example.demo.entity.ArticleInfo;
import com.example.demo.entity.UserInfo;
import com.example.demo.entity.vo.ArticleInfoVO;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/art")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/add")
    // 也需要 request 这个参数，用来取 uid
    // 因为前端没给咱传uid（防止黑客） 所以咱要从session里自己取
    public AjaxResult add(ArticleInfo articleInfo, HttpServletRequest request){
        // 1、非空校验
        if(articleInfo == null ||
                !StringUtils.hasLength(articleInfo.getTitle()) ||
                !StringUtils.hasLength(articleInfo.getContent()))
            return AjaxResult.fail(-1,"参数异常");
        // 2、组装数据， 得到uid
        UserInfo userInfo = UserSessionTools.getLoginUser(request);
        // 将括号内的值赋值到articleInfo自身的 uid 里面
        articleInfo.setUid(userInfo.getId());
        // 3、持久化，将结果返回给前端
        // 给前端返回数据库受影响的行数
        int result = articleService.add(articleInfo);
        return AjaxResult.success(result);
    }

    // 个人博客列表页用
    // 获取文章的详情信息，但需要进行鉴权（判断权限的归属人是否是当前登录用户）
    // 在数据库查询是该人的博客（对比id和uid），并将其返回
    // 返回的是具体的某个博客（Article表的一行数据，带标题，主题等部分），返回的是 ArticleInfo ，不过把它转换成ajax了
    // 放到 ajax 的 data 部分，然后前端可以用 res.data  .id .uid .title 等进行操作
    @RequestMapping("/getdetailbyid")
    public AjaxResult getDetailByIdAndUid(Integer id, HttpServletRequest request) {
        if(id == null || id <= 0)
            return AjaxResult.fail(-1, "非法参数");
        UserInfo userInfo = UserSessionTools.getLoginUser(request);
        return AjaxResult.success(articleService.getDetailByIdAndUid(id, userInfo.getId()));
    }

    // 修改博客
    @RequestMapping("/update")
    public AjaxResult update(ArticleInfo articleInfo, HttpServletRequest request) {
        // 1、非空校验
        if (articleInfo == null || articleInfo.getId() <= 0 ||
                !StringUtils.hasLength(articleInfo.getTitle()) ||
                !StringUtils.hasLength(articleInfo.getContent()))
            return AjaxResult.fail(-1, "参数有误！");
        // 2、获取登录用户的id，填充到 articleinfo 对象中 (修改时要验证权限)
        UserInfo userInfo = UserSessionTools.getLoginUser(request);
        articleInfo.setUid(userInfo.getId());
        // 修改时间
        articleInfo.setUpdatetime(LocalDateTime.now());
        // 此处返回受影响的行数
        int result = articleService.update(articleInfo);
        return AjaxResult.success(result);
    }

    // 查询文章详情
    @RequestMapping("/getdetail")
    public AjaxResult getDetail(Integer id) {
        if (id == null || id <= 0)
            return AjaxResult.fail(-1, "参数有误");
        ArticleInfoVO articleInfoVO = articleService.getDetail(id);
        return AjaxResult.success(articleInfoVO);
    }

    // 增加阅读量
    @RequestMapping("/addrcount")
    public AjaxResult ajaxResult(Integer id) {
        if (id == null || id <= 0)
            return AjaxResult.fail(-1, "参数错误！");
        int result = articleService.addRCount(id);
        return AjaxResult.success(result);
    }

    // 前端列表展示页面 —— 多个博客
    @RequestMapping("/mylist")
    public AjaxResult mylist(HttpServletRequest request) {
        UserInfo userInfo =UserSessionTools.getLoginUser(request);
        List<ArticleInfo> list = articleService.getListByUid(userInfo.getId());
        // 将文章正文截取成文章摘要
        for (ArticleInfo item : list) {
            String content = StringTools.subLength(item.getContent(), 200);
            // todo: 后续功能一定要补充 —— 将 content 里面的 markdown关键字去除掉
            item.setContent(content);
        }
        return AjaxResult.success(list);
    }

    // 删除
    @RequestMapping("/del")
    public AjaxResult del(Integer id, HttpServletRequest request) {
        if (id == null || id <= 0)
            return AjaxResult.fail(-1, "参数错误！");
        UserInfo userInfo = UserSessionTools.getLoginUser(request);
        int result = articleService.del(id, userInfo.getId());
        return AjaxResult.success(result);
    }

    // pageSize 是每页显示最大数   pageIndex 是当前页码   offset 是公式处理过后的供数据库查询的参数
    @RequestMapping("/getlistbypage")
    public AjaxResult getListByPage(Integer pageSize, Integer pageIndex) {
        // 默认值处理
        if (pageSize == null || pageSize <= 0)
            pageSize = 2;
        if (pageIndex == null || pageIndex < 1)
            pageIndex = 1;
        // 分页公式
        int offset = (pageIndex -1) * pageSize;
        List<ArticleInfo> list = articleService.getListByPage(pageSize, offset);
        // 多线程并发循环
        list.stream().parallel().forEach(item -> {
            item.setContent(StringTools.subLength(item.getContent(), 150));
        });
        return AjaxResult.success(list);
    }

    @RequestMapping("/getcount")
    public AjaxResult getCount(){
        return AjaxResult.success(articleService.getCount());
    }

}
