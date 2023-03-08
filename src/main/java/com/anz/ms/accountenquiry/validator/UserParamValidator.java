package com.anz.ms.accountenquiry.validator;

import com.anz.ms.accountenquiry.exception.UserParameterInvalidException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserParamValidator {

    public void validateUserCode(String userCode) {
        if (StringUtils.isBlank(userCode)) {
            log.error("User code is blank");

            throw new UserParameterInvalidException("User code is blank");
        }
    }

    public void validateAccountNumber(String accountNumber) {
        if (StringUtils.isBlank(accountNumber)) {
            log.error("Account number code is blank");

            throw new UserParameterInvalidException("Account number code is blank");
        }
    }
}
