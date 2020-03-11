package ru.goncharov.application.constants;

/**
 * Created on 28.02.2020.
 */
public class ConstantQuery {


//    public static final String URL= "jdbc:postgresql://localhost:5432/mybd";
    public static final String URL= "jdbc:postgresql://ural:5432/getdb";

    public static final String USER="tkach";
    //    public static final String USER="postgres";


//    public static final String PASSWORD="1111";
    public static final String PASSWORD="";

    public final static String  GET_USERS = "WITH subtable1 AS  "
            +"(SELECT users.user_id AS id ,concat(user_surname ,' ', user_name) ,user_flag  FROM users "
            +"LEFT JOIN users_order "
            + "ON  users_order.user_id=users.user_id ) "
            + "SELECT *  FROM subtable1 WHERE concat NOT IN (' ') "
            + "ORDER BY id ";
    public final static String GET_PROJECTS ="with subtable as "
            + "(select unnest(projects_id)as p_id from users_order "
            + "where user_id=?) "
            + "select project_id,project_name, "
            + "case when p_id is null then false "
            + "else true "
            +"end flag "
            + "from subtable "
            +"right join projects "
            + "on  p_id=project_id "
            + "order by project_name ";
    public final static String SAVE_USERS ="UPDATE users_order SET user_flag=?  WHERE user_id=?";
    public final static String SAVE_PROJECTS_NULL ="UPDATE users_order SET projects_id=NULL  WHERE user_id=?";
    public final static String SAVE_PROJECTS="UPDATE users_order SET projects_id[?]=?  WHERE user_id=?";
}

