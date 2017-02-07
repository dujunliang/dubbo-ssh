package com.gov.sys.service;

import com.gov.sys.data.dto.UserDTO;
import org.springframework.stereotype.Service;

/**
 * Created by dujunliang on 16/11/21.
 */
public interface UserService {


    public String ServiceDemo(String myName);

    public boolean loginUser(UserDTO dto);


}
