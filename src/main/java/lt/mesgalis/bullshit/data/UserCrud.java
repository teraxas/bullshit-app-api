package lt.mesgalis.bullshit.data;

import lt.mesgalis.bullshit.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserCrud extends CrudRepository<User, Long> {
}
