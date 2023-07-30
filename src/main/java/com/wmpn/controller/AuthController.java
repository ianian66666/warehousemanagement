package com.wmpn.controller;

import com.wmpn.entity.Auth;
import com.wmpn.entity.Result;
import com.wmpn.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    /**
     * 獲取權限樹
     *
     * @return
     */
    @GetMapping("/auth-tree")
    public Result getAuthTree() {
        List<Auth> authList = authService.queryAllAuthTree();
        return Result.ok(authList);

    }

}
