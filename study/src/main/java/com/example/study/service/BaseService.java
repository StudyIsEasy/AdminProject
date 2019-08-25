package com.example.study.service;

import com.example.study.model.network.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public abstract class BaseService<Request,Response,T> {

    @Autowired(required = false)
    protected JpaRepository<T,Long> baseRepository;

    public abstract Header<Response> create(Header<Request> request);

    public abstract Header<Response> read(Long id);

    public abstract Header<Response> update(Header<Request> request);

    public abstract Header delete(Long id);

}
