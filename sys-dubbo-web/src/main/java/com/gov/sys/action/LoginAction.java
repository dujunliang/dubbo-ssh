package com.gov.sys.action;

import com.gov.sys.data.dto.UserDTO;
import com.gov.sys.service.UserService;
import com.gov.sys.struts.BaseAction;
import com.gov.sys.struts.annotation.Access;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Created by dujunliang on 16/11/23.
 */
@Controller
@Scope("prototype")
public class LoginAction extends BaseAction<UserDTO> {


    @Autowired
    private UserService userService;

    public String login() throws Exception {
        return SUCCESS;
    }

    @Access(funcCode = "LOGIN_ING")
    public String index() throws Exception {

        System.out.println("coming:" + model.getUserName());

        if(!checkLogin()){
            userService.loginUser(model);
            putToHttpSession("username", model.getUserName());
            putToSession("user", model);
            ActionContext.getContext().getValueStack().push(model);
        }else{

            ActionContext.getContext().getValueStack().push(getToHttpSession("username"));
        }
        return SUCCESS;
    }

    public boolean checkLogin() {
        Object o = getFromSession("user");
        if (o != null) {
            return true;
        } else {
            return false;
        }
    }


}
