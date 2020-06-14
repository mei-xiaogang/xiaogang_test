package com.team.housebackapi.controller;

import com.github.pagehelper.PageInfo;
import com.team.housebackapi.entity.House;
import com.team.housebackapi.entity.Users;
import com.team.housebackapi.service.HouseService;
import com.team.housebackapi.util.BaseResult;
import com.team.housebackapi.util.FileUploadUtil;
import com.team.housebackapi.util.PageParmeter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/house/")
public class HouseController {
	@Autowired
	private HouseService houseService;
	//发布出租房
	@RequestMapping("fabuHouse")
	public BaseResult fabuHouse(
			House house,   //接收输入的内容
			@RequestParam(value = "pfile",required = false)
					MultipartFile pfile){
		//一、实现文件上传
		//注意：a.将上传的文件保存到文件服务器中(d://images充当文件服务器)
		//     b.一个上传的文件域对应一个MultipartFile类的对象
		try {
			//利用上传文件的工具类实现上传文件
			String path="E:\\hello";
			String fileName=FileUploadUtil.upload(pfile,path);
			//二、将输入的出租房信息保存到数据库
			//设置出租房的随机唯一编号(采用时间毫秒)
			house.setId(System.currentTimeMillis()+"");
			//设置上传文件路径
			house.setPath(fileName);
			//调用业务保存信息
			houseService.addHouse(house);
			return new BaseResult(BaseResult.RESULT_SUCCESS,"");
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResult(BaseResult.RESULT_FAIL,"发布失败:"+e.getMessage());
		}
	}
	//获取用户出租房信息  传递页码page
	@RequestMapping("getHouseByPage")
	public BaseResult getHouseByPage(PageParmeter pageParmeter, HttpSession session){
		//调用业务获取分页信息
		//假如登入没有实现，获取不到session时，固定用户编号
		Users users=(Users) session.getAttribute("logininfo");
		Integer userid=users.getId();   //获取session的用户编号
		PageInfo<House> pageInfo=this.houseService.getHouseByUser(userid,pageParmeter);
		//返回总页数，当前页的数据
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("totalPage",pageInfo.getPages());  //总页数
		map.put("row",pageInfo.getList()); //当前页数据
		return new BaseResult(200,map);
	}
}
