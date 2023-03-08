package com.anz.ms.accountenquiry.validator;

import com.anz.ms.accountenquiry.exception.UserParameterInvalidException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
public class UserParamValidator {

    private final Pattern specialCharacterPattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);

    public void validateUserCode(String userCode) {
        validateBlank(userCode, "User code is blank");
    }

    public void validateAccountNumber(String accountNumber) {
        validateBlank(accountNumber, "Account number code is blank");
        validateSpecialCharacters(accountNumber, "Account number contains special characters");
    }

    private void validateBlank(String value, String message) {
        if (StringUtils.isBlank(value)) {
            log.error(message);

            throw new UserParameterInvalidException(message);
        }

        log.debug("Value {} is validated as not blank", value);
    }

    private void validateSpecialCharacters(String value, String message) {
        Matcher specialCharacterNumberMatcher = specialCharacterPattern.matcher(value);

        boolean match = specialCharacterNumberMatcher.find();

        if (match) {
            log.error(message);

            throw new UserParameterInvalidException(message);
        }

        log.debug("Value {} is validated as not containing special characters", value);
    }
}
