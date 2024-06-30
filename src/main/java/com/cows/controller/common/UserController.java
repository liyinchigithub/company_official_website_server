package com.cows.controller.common;

import com.cows.dto.*;
import com.cows.entity.User;
import com.cows.service.UserService;
import com.cows.commons.api.BaseResponse;
import com.cows.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 一个UserController类，其中包含处理用户相关请求的方法。
 * 层级：控制器->服务层->数据访问层
 * 作用：处理用户相关的请求
 * 在这个例子中，UserController中的每个方法都会将 User对象 转换为 UserDTO对象，然后返回UserDTO对象。
 * convertToDto和convertToEntity方法用于在User和UserDTO之间进行转换。
 * @author liyinchi
 * @date 2024/06/29
 */

@Slf4j
@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @author: liyinchi
     * @description 通过获取所有用户信息
     * @mark
     */
    @Operation(summary = "获取所有用户信息", description = "返回所有用户的列表")
    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public BaseResponse<List<UserDTO>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDTO> userDTOs = users.stream().map(this::convertToDto).collect(Collectors.toList());
        log.info("=======getAllUsers: " + userDTOs);
        return BaseResponse.success(userDTOs);
    }

    /**
     * @author: liyinchi
     * @description 通过ID获取用户信息
     * @mark /getUserById/{id}
     */
    @Operation(summary = "通过ID获取用户信息", description = "返回指定ID的用户")
    @RequestMapping(value = "/getUserById/{id}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public BaseResponse<UserDTO> getUserById(@Parameter(description = "用户ID", required = true) @PathVariable int id) {
        User user = userService.getUserById(id);
        log.info("=======getUserById user:{}", user);
        return BaseResponse.success(convertToDto(user));
    }

    /**
     * @author: liyinchi
     * @description 通过ID获取用户信息
     * @mark ?id=1&name=liyinchi
     */
    @Operation(summary = "通过ID参数获取用户信息", description = "返回指定ID的用户")
    @RequestMapping(value = "/getUserByIdParam", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public BaseResponse<UserDTO> getUserByIdParam(@Parameter(description = "用户ID", required = true) @RequestParam("id") int id) {
        User user = userService.getUserById(id);
        log.info("=======getUserByIdParam user:{}", user);
        return BaseResponse.success(convertToDto(user));
    }

    /**
     * @author: liyinchi
     * @description 新增用户
     * @mark json
     */
    // 定义一个处理POST请求的方法，路径为"/addUser"，返回的数据类型为JSON
    @Operation(summary = "新增用户", description = "通过JSON数据新增用户")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    // 该方法接收一个参数，即UserDTO对象，这个对象是通过@RequestBody注解从请求体中获取的
    public BaseResponse<UserIdResponseDTO> addUser(@Parameter(description = "用户数据", required = true) @RequestBody UserDTO userDto) {
        // 调用convertToEntity方法，将UserDTO对象转换为User对象
        User user = convertToEntity(userDto);
        // 调用userService的addUser方法，将User对象添加到数据库中
        int newUserId = userService.addUser(user);
        // 记录日志，输出添加的User对象的信息
        log.info("=======addUser:{}", user);
        // 返回一个表示操作成功的BaseResponse对象
        return BaseResponse.success(new UserIdResponseDTO(newUserId));
    }


    /**
     * @author: liyinchi
     * @description 测试发生运行时异常时、Error时 事务回滚
     * @mark json
     */
    @PostMapping("/insertUser")
    public String insertUser(@RequestBody User user) throws Exception {
        if (null != user) {
            userService.insertUser(user);
            return "success";
        } else {
            return "false";
        }
    }

    /**
     * @author: liyinchi
     * @description 新增用户
     * @mark 表单
     */
    // 定义一个处理POST请求的方法，路径为"/addUserFormData"，返回的数据类型为JSON
    @Operation(summary = "新增用户（表单）", description = "通过表单数据新增用户")
    @RequestMapping(value = "/addUserFormData", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    // 该方法接收三个参数，分别是id，userName和password，这些参数都是通过@RequestParam注解从请求中获取的
    public BaseResponse<UserIdResponseDTO> addUserFormData(@Parameter(description = "用户名", required = true) @RequestParam("userName") String userName,
                                                           @Parameter(description = "密码", required = true) @RequestParam("password") String password) {
        // 创建一个新的UserDTO对象
        UserDTO userDto = new UserDTO();
        // 将从请求中获取的参数设置到UserDTO对象中
        userDto.setUserName(userName);
        userDto.setPassword(password);
        // 调用convertToEntity方法，将UserDTO对象转换为User对象
        User user = convertToEntity(userDto);
        // 记录日志，输出添加的User对象的信息
        log.info("=======addUserFormData:{}", user);
        // 调用userService的addUser方法，将User对象添加到数据库中
        int newUserId = userService.addUser(user);
        // 返回一个表示操作成功的BaseResponse对象
        return BaseResponse.success(new UserIdResponseDTO(newUserId));
    }

    /**
     * @author: liyinchi
     * @description 更新用户
     * @mark json
     */
    @Operation(summary = "更新用户", description = "通过JSON数据更新用户")
    // 定义一个处理PUT请求的方法，路径为"/updateUser"，返回的数据类型为JSON
    @RequestMapping(value = "/updateUser", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    // 该方法接收一个参数，即UserDTO对象，这个对象是通过@RequestBody注解从请求体中获取的
    public BaseResponse<UpdateUserResponseDTO> updateUser(@Parameter(description = "用户数据", required = true) @RequestBody UserDTO userDto) {
        // 调用convertToEntity方法，将UserDTO对象转换为User对象
        User user = convertToEntity(userDto);
        // 调用userService的updateUser方法，将User对象的信息更新到数据库中
        int updates = userService.updateUser(user);
        // 记录日志，输出更新的User对象的信息
        log.info("=======updateUser:{}", user);
        // 返回一个表示操作成功的BaseResponse对象
        return BaseResponse.success(new UpdateUserResponseDTO(user.getId(), updates));
    }

    /**
     * @param request object {"id":4}
     * @return object
     * @author: liyinchi
     * @description 删除用户
     */
    // 定义一个处理DELETE请求的方法，路径为"/deleteUser"，返回的数据类型为JSON
    @Operation(summary = "删除用户", description = "通过JSON数据删除用户")
    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
    // 该方法接收一个参数，即DeleteUserRequestDTO对象，这个对象是通过@RequestBody注解从请求体中获取的
    public BaseResponse<DeleteUserResponseDTO> deleteUser(@Parameter(description = "删除用户请求数据", required = true) @RequestBody DeleteUserRequestDTO request) {
        // 调用userService的deleteUser方法，根据请求中的id删除数据库中的用户
        int updates = userService.deleteUser(request.getId());// 获取请求参数id值
        // 记录日志，输出删除的用户的请求信息
        log.info("=======deleteUser:{}", request);
        // 返回一个表示操作成功的BaseResponse对象
        return BaseResponse.success(new DeleteUserResponseDTO(request.getId(), updates));
    }

    /**
     * @param id 用户ID
     * @return String
     * @author: liyinchi
     * @description 删除用户
     * @mark 请求参数在URI中
     */
    @Operation(summary = "通过ID删除用户", description = "删除指定ID的用户")
    @RequestMapping(value = "/deleteUserPath/{id}", method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
    public BaseResponse<DeleteUserResponseDTO> deleteUserPath(@Parameter(description = "用户ID", required = true) @PathVariable int id) { // id在URI中
        // 调用userService的deleteUser方法，根据请求中的id删除数据库中的用户
        int updates = userService.deleteUser(id);// 获取请求参数id值
        log.info("=======deleteUserPath:{}", id);
        return BaseResponse.success(new DeleteUserResponseDTO(id, updates));
    }

    /**
     * @param id 用户ID
     * @return object
     * @author: liyinchi
     * @description 删除用户
     * @mark 请求参数在URI中 ?id=
     */
    @Operation(summary = "通过ID参数删除用户", description = "删除指定ID的用户")
    @RequestMapping(value = "/deleteUserParam", method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
    public BaseResponse<DeleteUserResponseDTO> deleteUserParam(@Parameter(description = "用户ID", required = true) @RequestParam("id") int id) { // id在URI中
        int updates = userService.deleteUser(id);// 获取请求参数id值
        log.info("=======deleteUserParam:{}", id);
        return BaseResponse.success(new DeleteUserResponseDTO(id, updates));
    }

    /**
     * @param page 页码
     * @param size 每页显示条数
     * @return object
     * @author: liyinchi
     * @description 分页查询
     */
    @GetMapping("/getAllUsersPagedSorted")
    public BaseResponse<List<UserDTO>> getAllUsersPagedSorted(@RequestParam int page, @RequestParam int size, @RequestParam String sortField) {
        List<User> users = userService.getAllUsers(page, size, sortField);
        List<UserDTO> userDTOs = users.stream().map(this::convertToDto).collect(Collectors.toList());
        return BaseResponse.success(userDTOs);
    }


    /**
     * dao 转 dto
     */
    private UserDTO convertToDto(User user) {
        UserDTO userDto = new UserDTO();
        userDto.setId(user.getId());
        userDto.setUserName(user.getUserName());
        userDto.setPassword(user.getPassword());
        userDto.setEnable(user.isEnable());
        userDto.setDeleted(user.isDeleted());
        log.info("=======convertToDto:{}", userDto);
        return userDto;
    }


    /**
     * dto 转 实体类
     */
    private User convertToEntity(UserDTO userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setEnable(userDto.isEnable());
        user.setDeleted(userDto.isDeleted());
        log.info("=======convertToEntity:{}", user);
        return user;
    }


}