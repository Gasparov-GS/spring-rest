package ru.gasparov.service;


import ru.gasparov.model.Role;

import java.util.HashSet;
import java.util.Set;

public class UtilService {

    public static Set<Role> valuesToSetRole(String[] values) {
        Set<Role> roleSet = new HashSet<>();
        for (String result :
                values) {
            if(!"Set role".equals(result)) {
                roleSet.add(new Role(result));
            }
        }
        return roleSet;
    }
}