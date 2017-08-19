package lt.mesgalis.bullshit.data;

import lt.mesgalis.bullshit.model.Status;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by terax on 2017-08-18.
 */
public interface StatusCrud extends CrudRepository<Status, Status.StatusKey> {
}
