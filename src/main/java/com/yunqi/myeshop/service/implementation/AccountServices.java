package com.yunqi.myeshop.service.implementation;

import com.yunqi.myeshop.entity.user.Account;
import com.yunqi.myeshop.entity.userdto.*;
import com.yunqi.myeshop.mapper.AccountMapper;
import com.yunqi.myeshop.service.interfaces.IAccountServices;
import com.yunqi.myeshop.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServices implements IAccountServices{

    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Override
    public AccountDetailDTO getAccountByAccountId(int account_id) {
        return accountMapper.findAccountByAccountId(account_id);
    }

    @Override
    public Optional<String> loginByUsername(LoginByUsernameDTO loginByUsernameDto) {
        String encryptedPwd = passwordEncoder.encode(loginByUsernameDto.getPassword());
        Account account=accountMapper.findAccountByUsername(loginByUsernameDto.getUsername());
        if(account!=null&&passwordEncoder.matches(encryptedPwd,account.getPassword_hash())){
            accountMapper.updateLoginTime(account.getAccount_id(),LocalDateTime.now());
            AccountJwtDTO accountJwtDTO=new AccountJwtDTO();
            copyAccountToJwtDTO(account,accountJwtDTO);
            return Optional.of(jwtUtils.generateToken(accountJwtDTO));
        }
        return Optional.empty();
    }

    @Override
    public List<AccountDetailDTO> getAllAccounts() {
        return accountMapper.findAllAccounts();
    }

    @Override
    public int changePassword(ChangePwdDTO changePwdDto) {
        String encryptedPwd = passwordEncoder.encode(changePwdDto.getPassword());
        changePwdDto.setPassword(encryptedPwd);
        return accountMapper.updateAccountPasswordHash(changePwdDto);
    }

    @Override
    public int changeUsername(ChangeUnameDTO changeUnameDto) {
        if(doesUsernameExist(changeUnameDto.getUsername()))
            return -1;
        return accountMapper.updateAccountUsername(changeUnameDto);
    }

    @Override
    public int changeEmail(ChangeEmailDTO changeEmailDto) {
        String new_email = changeEmailDto.getEmail();
        if(doesEmailExist(new_email))
            return -1;
        return accountMapper.updateAccountEmail(changeEmailDto);
    }

    @Override
    public int changePhoneNumber(ChangePhoneNoDTO changePhoneNoDto) {
        if(doesPhoneNumberExist(changePhoneNoDto.getPhone_number()))
            return -1;
        return accountMapper.updateAccountPhoneNumber(changePhoneNoDto);
    }

    /**
     * Insert a new account into database
     * @param account
     * @return Return value is greater than 0 if username,phone number or email already exists in database.
     * 0 for fail to insert, 1 for successfully inserted.
     */
    @Override
    public int registerAccount(AccountRegisterDTO account) {
        String encryptedPwd= passwordEncoder.encode(account.getPassword());
        account.setPassword(encryptedPwd);
        String username=account.getUsername();
        String email=account.getEmail();
        String phone_number=account.getPhone_number();
        int validity=checkValidityOfAccountDetailsForRegister(username,email,phone_number);
        if(validity!=0)
            return validity;
        int retryCounter=3;
        while(retryCounter>0){
            account.setAccount_uid(UUID.randomUUID().toString());
            account.setCreated_at(LocalDateTime.now());
            try{
                return accountMapper.insertAccount(account);
            }
            catch(Exception e){
                retryCounter--;
            }
        }
        return 0;
    }

    @Override
    public int deleteAccountByAccountId(int account_id) {
        return accountMapper.deleteAccountByAccountId(account_id);
    }

    /**
     * Test if username, email, phone number exist in database.
     * @param username
     * @param email
     * @param phone_number
     * @return
     * 0(0b000) All don't exist
     * 1(0b001) Username exists
     * 2(0b010) email exists
     * 4(0b100) phone number exists
     * 3(0b011) username,email exist
     * 5(0b101) phone number, username exist
     * 6(0b110) phone number, email exist
     * 7(0b111) All exist
     */
    private int checkValidityOfAccountDetailsForRegister(String username, String email, String phone_number) {
        int validityState=0;
        String uid_by_uname=accountMapper.findAccountUIdByUsername(username);
        String uid_by_email=accountMapper.findAccountUIdByEmail(email);
        String uid_by_phone=accountMapper.findAccountUIdByPhoneNo(phone_number);
        if(uid_by_uname!=null)
            validityState|=0b001;
        if(uid_by_email!=null)
            validityState|=0b010;
        if(uid_by_phone!=null)
            validityState|=0b100;
        return validityState;
    }

    private boolean doesUsernameExist(String new_username) {
        String uid_by_uname=accountMapper.findAccountUIdByUsername(new_username);
        return uid_by_uname!=null;
    }
    private boolean doesEmailExist(String new_email) {
        String uid_by_email=accountMapper.findAccountUIdByEmail(new_email);
        return uid_by_email!=null;
    }
    private boolean doesPhoneNumberExist(String new_phone_number) {
        String uid_by_phone=accountMapper.findAccountUIdByPhoneNo(new_phone_number);
        return uid_by_phone!=null;
    }
    private void copyAccountToJwtDTO(Account from,AccountJwtDTO accountJwtDTO)
    {
        accountJwtDTO.setAccount_id(from.getAccount_id());
        accountJwtDTO.setAccount_uid(from.getAccount_uid());
        accountJwtDTO.setUsername(from.getUsername());
        accountJwtDTO.setRole(from.getRole());
    }
}
