package com.anz.ms.accountenquiry.validator;

import com.anz.ms.accountenquiry.exception.UserParameterInvalidException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is used to validate the Path Variable parameters.
 */
@Component
@Slf4j
public class UserParamValidator {

    private final Pattern specialCharacterPattern = Pattern.compile("[^a-z0-9_]", Pattern.CASE_INSENSITIVE);

    /**
     * Validate the User Code.
     * @param userCode User Code
     */
    public void validateUserCode(String userCode) {
        validateBlank(userCode, "User code is blank");
    }

    public void validateAccountId(Long accountId) {
        if (accountId <= 0) {
            log.debug("Account id: {} is invalid", accountId);

            throw new UserParameterInvalidException("Account id is invalid");
        }
    }

    /**
     * Validate the Account Number.
     * @param accountNumber Account Number
     */
    public void validateAccountNumber(String accountNumber) {
        validateBlank(accountNumber, "Account number code is blank");
        validateSpecialCharacters(accountNumber, "Account number contains special characters");
    }

    /**
     * Validate null and black space in a given string and throw UserParameterInvalidException if fails.
     * @param value Value
     * @param message Error Message upon failure
     */
    private void validateBlank(String value, String message) {
        if (StringUtils.isBlank(value)) {
            log.error(message);

            throw new UserParameterInvalidException(message);
        }

        log.debug("Value {} is validated as not blank", value);
    }

    /**
     * Validate special characters in a given string and throw UserParameterInvalidException if fails.
     * @param value Value
     * @param message Error Message upon failure
     */
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
