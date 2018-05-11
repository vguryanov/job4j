package ru.job4j.generics.stores;

import ru.job4j.generics.baseclasses.Role;

/**
 * Created by User2 on 10.05.2018.
 */
public class RoleStore extends BaseStore<Role> {
    public RoleStore(int capacity) {
        super(capacity);
    }
}
