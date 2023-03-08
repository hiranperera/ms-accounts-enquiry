package com.anz.ms.accountenquiry.validator;

import com.anz.ms.accountenquiry.exception.UserParameterInvalidException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserParamValidator {

    public void validateUserCode(String userCode) {
        validateBlank(userCode, "User code is blank");
    }

    public void validateAccountNumber(String accountNumber) {
        validateBlank(accountNumber, "Account number code is blank");
    }

    private void validateBlank(String userCode, String s) {
        if (StringUtils.isBlank(userCode)) {
            log.error(s);

            throw new UserParameterInvalidException(s);
        }
    }
}
