package com.jshine.performance.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jshine.performance.system.model.SysUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface SysUserInfoMapper extends BaseMapper<SysUserInfo> {
  List<SysUserInfo> findList();
  List<SysUserInfo> testFindPage(IPage page, @Param("ew") QueryWrapper<SysUserInfo> wrapper);
}
