package crm.settings.web.service;

import crm.settings.domain.User;

import java.util.Map;

public interface UserService {
    User queryUserByLoginActAndPwd(Map<String, Object> map);
}
