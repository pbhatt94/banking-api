package com.wg.banking.specification;

import org.springframework.data.jpa.domain.Specification;

import com.wg.banking.filter.UsersFilter;
import com.wg.banking.model.Role;
import com.wg.banking.model.User;

public class UserSpec {

	public static final String NAME = "name";
    public static final String USERNAME = "username";
    public static final String AGE = "age";
    public static final String ADDRESS = "address";
    public static final String ROLE = "role";

    private UserSpec() {
    }

    public static Specification<User> filterBy(UsersFilter usersFilter) {
        return Specification
                .where(hasUsername(usersFilter.getUsername()))
                .and(hasName(usersFilter.getName()))
                .and(hasAge(usersFilter.getAge()))
                .and(hasAddress(usersFilter.getAddress()))
                .and(hasRole(usersFilter.getRole()));
    }

    private static Specification<User> hasUsername(String username) {
        return (root, query, cb) -> username == null || username.isEmpty() 
            ? cb.conjunction() 
            : cb.equal(root.get(USERNAME), username);
    }
    
    private static Specification<User> hasName(String name) {
    	return (root, query, cb) -> name == null || name.isEmpty() 
                ? cb.conjunction() 
                : cb.like(root.get(NAME), "%" + name + "%");
    }

    private static Specification<User> hasAge(Integer age) {
        return (root, query, cb) -> age == null 
            ? cb.conjunction() 
            : cb.equal(root.get(AGE), age);
    }

    private static Specification<User> hasAddress(String address) {
        return (root, query, cb) -> address == null || address.isEmpty() 
            ? cb.conjunction() 
            : cb.like(root.get(ADDRESS), "%" + address + "%");
    }

    private static Specification<User> hasRole(Role role) {
        return (root, query, cb) -> role == null 
            ? cb.conjunction() 
            : cb.equal(root.get(ROLE), role);
    }
}