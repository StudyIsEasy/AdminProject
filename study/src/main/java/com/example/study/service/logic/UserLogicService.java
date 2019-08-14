package com.example.study.service.logic;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.User;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        UserApiRequest body = request.getData();

        User user = User.builder()
                .account(body.getAccount())
                .password(body.getPassword())
                .status("REGISTERED")
                .email(body.getEmail())
                .phoneNumber(body.getPhoneNumber())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = userRepository.save(user);

        return userResponse(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        User user = userRepository.getOne(id);
        return userResponse(user);
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {

        UserApiRequest body = request.getData();
        Long id = body.getId();
        User user = userRepository.getOne(id);

        user.setPassword(body.getPassword())
                .setStatus("REGISTERED")
                .setEmail(body.getEmail())
                .setPhoneNumber(body.getPhoneNumber())
                ;

        User newUser = userRepository.save(user);
        return userResponse(newUser);
    }

    @Override
    public Header delete(Long id) {
        userRepository.findById(id).ifPresent(user->userRepository.delete(user));
        return Header.OK();
    }


    private Header<UserApiResponse> userResponse(User user){

        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword())
                .status(user.getStatus())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        return Header.OK(userApiResponse);
    }
}
