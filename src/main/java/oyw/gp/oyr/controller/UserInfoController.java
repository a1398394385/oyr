package oyw.gp.oyr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import oyw.gp.oyr.entity.Response;
import oyw.gp.oyr.entity.User;
import oyw.gp.oyr.service.UserService;


/**
 * UserInfoController
 */
@RestController
@RequestMapping("/info")
public class UserInfoController
{
    @Autowired
    UserService userService;

    @PatchMapping(value = "/telephone")
    public Response<Object> telephone(@RequestBody User user) {
        if (userService.updateById(user))
            return Response.result(200);
        else
            return Response.error(400, "用户不存在");
    }

    @PatchMapping(value = "/username")
    public Response<Object> username() {

        return null;
    }

    @PatchMapping(value = "/password")
    public Response<Object> password() {

        return null;
    }

    @PatchMapping(value = "/adress")
    public Response<Object> adress() {

        return null;
    }


}
