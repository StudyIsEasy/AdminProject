package com.example.study.controller.api;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.service.logic.UserLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserApiController implements CrudInterface<UserApiRequest, UserApiResponse> {


    @Autowired
    private UserLogicService userLogicService;


    @Override
    @PostMapping("")    // /api/user
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
        return userLogicService.create(request);
    }

    @Override
    @GetMapping("{id}") // /api/user/{id}
    public Header read(@PathVariable(name = "id") Long id) {
        return userLogicService.read(id);
    }

    @Override
    @PutMapping("") // /api/user
    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> request) {
        return userLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")  // /api/user/{id}
    public Header delete(@PathVariable Long id) {
        return userLogicService.delete(id);
    }
}
