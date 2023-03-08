package unit.com.anz.ms.accountenquiry.validator;

import com.anz.ms.accountenquiry.exception.UserParameterInvalidException;
import com.anz.ms.accountenquiry.validator.UserParamValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserParamValidatorTest {

    private UserParamValidator userParamValidator;

    @BeforeEach
    public void setup() {
        userParamValidator = new UserParamValidator();
    }

    @Test
    public void validateParamValid() {
        userParamValidator.validateAccountNumber("ACC0001");
    }

    @Test
    public void validateParamInvalid() {
        UserParameterInvalidException exception = assertThrows(UserParameterInvalidException.class, () -> userParamValidator.validateUserCode(" "));

        assertEquals(exception.getMessage(), "User code is blank");
    }

    @Test
    public void validateParamSpecialCharacters() {
        UserParameterInvalidException exception = assertThrows(UserParameterInvalidException.class, () -> userParamValidator.validateAccountNumber("ABC#"));

        assertEquals(exception.getMessage(), "Account number contains special characters");
    }
}
