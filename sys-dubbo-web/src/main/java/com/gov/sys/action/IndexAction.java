package com.gov.sys.action;

import com.gov.sys.data.dto.UserDTO;
import com.gov.sys.service.UserService;
import com.gov.sys.struts.BaseAction;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Created by dujunliang on 16/11/23.
 */
@Controller
@Scope("prototype")
public class IndexAction extends BaseAction<UserDTO> {


    @Autowired
    private UserService userService;


    public String index() throws Exception {

        System.out.println("index");

        ActionContext.getContext().getValueStack().push(getFromSession("user"));

        return SUCCESS;

    }


}
