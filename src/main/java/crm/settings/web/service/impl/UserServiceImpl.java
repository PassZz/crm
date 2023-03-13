package crm.settings.web.service.impl;

import crm.settings.domain.User;
import crm.settings.web.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service("UserService")
public class UserServiceImpl implements crm.settings.web.service.UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User queryUserByLoginActAndPwd(Map<String, Object> map) {
        return userMapper.selectUserByLoginActAndPwd(map);
    }
}
