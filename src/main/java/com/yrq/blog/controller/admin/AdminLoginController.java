package com.yrq.blog.controller.admin;

import com.yrq.blog.entity.Type;
import com.yrq.blog.entity.User;
import com.yrq.blog.service.BlogService;
import com.yrq.blog.service.TypeService;
import com.yrq.blog.service.UserService;
import com.yrq.blog.vo.BlogSearchVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @program: blog_yrq
 * @description:
 * @author: yrq
 * @create: 2020-03-24 01:21
 **/
@Controller
@RequestMapping("/admin")
public class AdminLoginController {
    @Autowired
    UserService userService;
    @Autowired
    BlogService blogService;
    @Autowired
    TypeService typeService;

    @GetMapping
    public String loginPage(){
        return "admin/login_admin";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes,
                        @RequestParam(value="page",required = false,defaultValue = "1") Integer page,
                        BlogSearchVO blog,
                        Model model){
        //shrio 权限管理
        Subject subject = SecurityUtils.getSubject();
        User user = userService.selectUser(username);
        System.out.println(user.getPassword());
        String pwd = new SimpleHash("MD5", password, user.getSalt(), 2).toString();
        System.out.println(user.getSalt()+"---------------------------------");
        System.out.println(pwd);
        UsernamePasswordToken token = new UsernamePasswordToken(username,pwd);
        System.out.println(pwd+"--------------------------------sanjdnaskdas--------------");
        //设置记住我
//        token.setRememberMe(true);
        if(!user.getPerms().equals("admin"))   {
            System.out.println(pwd+"------------------2--------------sanjdnaskdas--------------");
            return "redirect:/login_admin";
        }
        try {
            System.out.println(pwd+"-------------3------------------sanjdnaskdas--------------");
            subject.login(token);
            System.out.println("登录成功！！！！！！！！！！！！！！！！！！！！！！！*********");
            session.setAttribute("user",user);
            blog.setPage(page);
            model.addAttribute("types",typeService.listType());
            model.addAttribute("page", blogService.SearchListBlog(blog,""));
            return "admin/blogs";
        }catch(UnknownAccountException uae){
            //用户名不存在
            model.addAttribute("msg","用户名不存在");
            System.out.println("用户名不存在！！！！！！！！！！！！！！！！！！！！！！！*********");
            return "redirect:/login_admin";
        }catch (IncorrectCredentialsException ice){
            //密码不存在
            model.addAttribute("msg","密码错误");
            System.out.println("密码错误！！！！！！！！！！！！！！！！！！！！！！！*********");
            return "redirect:/login_admin";
        }catch (Exception e){
//            logger.info("登录失败，失败原因：[{}]", e.getMessage());
            e.printStackTrace();
            return "redirect:/login_admin";
        }

    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "admin/login_admin";
    }

    @GetMapping("/fun/love")
    public String love(){
        return "fun/love";
    }

    @GetMapping("/fun/photo")
    public String photo(){
        return "fun/photo";
    }


}
