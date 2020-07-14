package com.todoapp.webapp.todoappwebapp.util;

public class Constants {

    private Constants() {}

    public static final String REQUEST_MAPPING_TODOS = "/todos";
    public static final String REQUEST_MAPPING_TODOS_WITH_TODO_ID_PATHVAR = "/todos/{todoId}";
    public static final String REQUEST_MAPPING_USERNAME = "/username";
    public static final String REQUEST_MAPPING_HANDLE_USER = "/handleUser";

    public static final String SERVICE_NAME_TODO_SERVICES = "todo-services";
    public static final String SERVICE_NAME_USER_SERVICES = "user-services";

    public static final String PRE_AUTHORIZE_ROLE_USER = "hasRole('ROLE_USER')";

    public static final String KEY_NAME = "name";
    public static final String KEY_USER = "user";
}
