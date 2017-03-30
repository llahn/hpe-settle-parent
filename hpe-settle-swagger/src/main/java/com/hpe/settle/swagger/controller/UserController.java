package com.hpe.settle.swagger.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hpe.settle.swagger.model.User;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value="/users")
public class UserController {
	static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

	/*
	 * 注解说明：
	 * @Api：用在类上，说明该类的作用
	 * @ApiOperation：用在方法上，说明方法的作用
	 * @ApiImplicitParams：用在方法上包含一组参数说明
	 * @ApiImplicitParam：用在@ApiImplicitParams注解中，指定一个请求参数的各个方面
	 * 		paramType：参数放在哪个地方
	 * 			header-->请求参数的获取：@RequestHeader
	 * 			query-->请求参数的获取：@RequestParam
	 * 			path（用于restful接口）-->请求参数的获取：@PathVariable
	 * 			body（不常用）
	 * 			form（不常用）
	 * 			name：参数名
	 * 			dataType：参数类型
	 * 			required：参数是否必须传
	 * 			value：参数的意思
	 * 			defaultValue：参数的默认值
	 * @ApiResponses：用于表示一组响应
	 * @ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息
	 * 		code：数字，例如400
	 * 		message：信息，例如"请求参数没填好"
	 * 		response：抛出异常的类
	 * @ApiModel：描述一个Model的信息（这种一般用在post创建的时候，使用@RequestBody这样的场景，请求参数无法使用@ApiImplicitParam注解进行描述的时候）
	 * @ApiModelProperty：描述一个model的属性
	 * 
	 * 以上这些就是最常用的几个注解了。
	 */
	
	
    @ApiOperation(value="获取用户列表", notes="")
    @RequestMapping(value={""}, method=RequestMethod.GET)
    public List<User> getUserList() {
        List<User> r = new ArrayList<User>(users.values());
        return r;
    }

    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value="", method=RequestMethod.POST)
    public String postUser(@RequestBody User user) {
        users.put(user.getId(), user);
        return "success";
    }

    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return users.get(id);
    }

    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @RequestBody User user) {
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return "success";
    }

    @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }
}
