package com.gov.sys.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.gov.sys.data.dto.UserDTO;
import com.gov.sys.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dujunliang on 16/11/21.
 */
@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {


    @Override
    public String ServiceDemo(String MyName) {



        System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] Hello " + MyName + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
        return "Hello " + MyName + ", response form provider: " + RpcContext.getContext().getLocalAddress();

    }

    @Override
    public boolean loginUser(UserDTO dto) {

        return true;
    }


}
