package component.com.anz.ms.accountenquiry.repository.db;

import com.anz.ms.accountenquiry.Application;
import com.anz.ms.accountenquiry.repository.db.UserRepository;
import com.anz.ms.accountenquiry.repository.db.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({SpringExtension.class})
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByUserCodeValidCodeTest() {
        User user = userRepository.findByUserCode("U0001");

        assertNotNull(user);
        assertEquals(user.getUserCode(), "U0001");
        assertEquals(user.getName(), "Hiran Perera");
    }

    @Test
    public void findByUserCodeInvalidCodeTest() {
        User user = userRepository.findByUserCode("INVALID_USER_CODE");

        assertNull(user);
    }
}
