package com.jshine.performance.system.controller;

import com.jshine.performance.exception.CustomException;
import com.jshine.performance.system.model.SysUserInfo;
import com.jshine.performance.system.model.UserBean;
import com.jshine.performance.system.service.SysUserInfoService;
import com.jshine.performance.utils.message.CodeEnum;
import com.jshine.performance.utils.message.ReturnMessage;
import com.jshine.performance.utils.message.ReturnMessageUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
	@Autowired
    SysUserInfoService sysUserInfoService;
  @RequestMapping("list")
  public ReturnMessage listUser() throws CustomException {
      List<SysUserInfo> list= sysUserInfoService.findList();
      ReturnMessage mes= ReturnMessageUtil.sucess(list);
      return mes;
  }
    @RequestMapping("page")
    public List<SysUserInfo> pageUser() throws CustomException {
        List<SysUserInfo> list= sysUserInfoService.findPage();
        ReturnMessage mes= ReturnMessageUtil.sucess(list);

        return list;
    }
    @RequestMapping("add")
    public ReturnMessage listUser(SysUserInfo user)  {
        ReturnMessage mes=ReturnMessageUtil.sucess();
        try {
            sysUserInfoService.addUser(user);
        }catch (Exception e){
            mes= ReturnMessageUtil.error(CodeEnum.BAD_REQUEST);
        }
        return mes;
    }
    @RequestMapping("userInfo")
    public ReturnMessage userInfo()  {
        Subject subject = SecurityUtils.getSubject();
        UserBean user = (UserBean)subject.getPrincipal();
        ReturnMessage mes=ReturnMessageUtil.sucess(user);
        return mes;
    }
    @RequestMapping("delete")
    public ReturnMessage delete()  {

        ReturnMessage mes=ReturnMessageUtil.sucess();
        try {
            sysUserInfoService.deleteEntity();
        }catch (Exception e){
            mes= ReturnMessageUtil.error(CodeEnum.BAD_REQUEST);
        }
        return mes;
    }
}
