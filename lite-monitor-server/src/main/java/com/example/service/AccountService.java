package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.dto.Account;
import com.example.entity.vo.request.ConfirmResetVO;
import com.example.entity.vo.request.CreateSubAccountVO;
import com.example.entity.vo.request.EmailResetVO;
import com.example.entity.vo.request.ModifyEmailVO;
import com.example.entity.vo.response.SubAccountVO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AccountService extends IService<Account>, UserDetailsService {
    Account findAccountByNameOrEmail(String text);
    String registerEmailVerifyCode(String type, String email, String address);
    String resetEmailAccountPassword(EmailResetVO info);
    String resetConfirm(ConfirmResetVO info);
    Boolean changePassword(int id, String oldPass, String newPass);
    String modifyEmail(int uid, ModifyEmailVO emailVO);
    void createSubAccount(CreateSubAccountVO createSubAccountVO);
    void deleteSubAccount(int uid);
    List<SubAccountVO> listSubAccount();
    /**
     * 根据 clientId 获取拥有该主机权限的管理员信息（包含admin）
     */
    List<Account> getMailByClientId(int clientId);
}
