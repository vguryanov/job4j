package ru.job4j.generics.stores;

import ru.job4j.generics.baseclasses.User;

/**
 * Created by User2 on 10.05.2018.
 */
public class UserStore extends BaseStore<User> {
    public UserStore(int capacity) {
        super(capacity);
    }
}
