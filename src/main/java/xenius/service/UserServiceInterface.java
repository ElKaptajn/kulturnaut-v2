package xenius.service;

import xenius.model.User;

import java.util.List;

public interface UserServiceInterface extends CrudServiceInterface<User, Long> {
    List<User> findByName(String name);
}
