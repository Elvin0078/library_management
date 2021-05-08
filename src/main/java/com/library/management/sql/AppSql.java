package com.library.management.sql;

import javax.swing.plaf.PanelUI;

public class AppSql {

    public static final String GET_BOOK_LIST = "select b.Book_Id ,b.Author ,b.Name,b.Language,b.No_Copies_Actual,b.No_Copies_Current,b.Category_id,b.Publication_year , c.Category_name from Book_Details b \n" +
            " inner Join  Category_Details c on b.Category_id =c.Category_id  where b.Active = 1;";

    public static final String ADD_BOOK = "Insert into book_Details(Book_Id, Name,Author,Language,No_Copies_Actual,No_Copies_Current,Category_id,Publication_year,Active)\n" +
            "Values (default,:name,:author,:Language,:No_Copies_Actual,:No_Copies_Current,:Category_id,:Publication_year,default);";

    public static final String GET_BOOK_BY_ID = "select b.Book_Id,b.Name,b.Author,b.Language,b.No_Copies_Actual,b.No_Copies_Current,b.Category_id,b.Publication_year , c.Category_name from Book_Details b  inner Join  Category_Details c on b.Category_id = c.Category_id  where b.Active = 1 and b.Book_Id=:Id;";

    public static final String UPDATE_BOOK = "update Book_Details set Name=:name,Language=:language,Author=:author,No_Copies_Actual=:no_copies_actual, No_Copies_Current=:no_copies_current, Category_Id=:Category_Id,Publication_year=:Publication_year where Book_Id=:book_id;";

    public static final String UPDATE_BOOK_CURRENT_COUNT="update Book_Details set No_Copies_Current = :no_Copies_Current where Book_Id=:book_Id;";

    public static final String DELETE_BOOK = "update Book_Details set Active=0 where Book_Id=:Book_Id;";


    public static final String SEARCH_BOOK = "    select b.Book_Id ,b.Author ,b.Name,b.Language,b.No_Copies_Actual,b.No_Copies_Current,b.Category_id,b.Publication_year , c.Category_name from Book_Details b \n" +
            "            inner Join  Category_Details c on b.Category_id =c.Category_id  where b.Active = 1 and  (b.Name Like '%:search%'  or b.Author Like '%:search%' or b.Language Like'%:search%');\n";

    public static final String GET_BOOK_CATEGORY_LIST = "select Category_id,Category_Name from Category_Details;";


    public static final String LOGIN_USER = "Select u.User_Id , u.Full_Name ,u.Username ,u.Password  ,u.Phone ,u.Date_Of_Birthday ,u.Registration_Date ,  u.User_Role_Id,r.Role_name \n" +
            "from User_Details u \n" +
            "inner join  Role_Details r on u.User_role_Id  = r.Role_Id where u.Active = 1 and u.Username=:username and u.password =:password;";


    public static final String GET_USER_BY_LOGIN = "Select u.User_Id , u.Full_Name ,u.Username ,u.Password  ,u.Phone ,u.Date_Of_Birthday ,u.Registration_Date ,  u.User_Role_Id,r.Role_name \n" +
            "from User_Details u \n" +
            "\t\tinner join  Role_Details r on u.User_role_Id  = r.Role_Id where u.Active = 1 and u.Username=:username;";


    public static final String ADD_USER = "INSERT INTO User_Details (User_Id,Full_Name ,Username ,Password  ,Phone ,Date_Of_Birthday  , User_Role_Id)\n" +
            "VALUES (default,:full_name,:username,:password,:phone,:date_of_birthday,:user_role_id);";

    public static final String DELETE_USER = "update User_Details set Active=0 where User_Id=:userId;";

    public static final String UPDATE_USER = "update User_Details set Full_Name=:fullname,Username=:username,Password=:password,Phone=:phone,Date_Of_Birthday=:dob ,User_Role_Id=:userRoleId where User_Id=:userId ;\n";

    public static final String GET_USER_LIST = "Select u.User_Id , u.Full_Name ,u.Username ,u.Phone ,u.Date_Of_Birthday ,u.Registration_Date ,  u.User_Role_Id,r.Role_name\n" +
            "\t\tfrom User_Details u inner join  Role_Details r on u.User_role_Id  = r.Role_Id where u.Active = 1;";

    public static final String GET_USER_BY_ID = "Select u.User_Id , u.Full_Name ,u.Username ,u.Password  ,u.Phone ,u.Date_Of_Birthday ,u.Registration_Date ,  u.User_Role_Id,r.Role_name \n" +
            "            from User_Details u \n" +
            "            inner join  Role_Details r on u.User_role_Id  = r.Role_Id where u.Active = 1 and u.User_Id=:userId;";


    public static final String GET_USER_ROLE_LIST = "Select Role_Id,Role_Name from Role_Details;";

    public static final String GET_TRANSACTİON_MAIN = "Select u.user_Id,u.Full_Name,u.Username,u.Date_Of_Birthday,u.Registration_Date,u.phone,b.Book_Id,b.Name,b.Author,b.Language,b.No_Copies_Actual,b.No_Copies_Current,b.publication_Year,t.tr_id,t.Tr_Date,t.Status\n" +
            "from Transaction_Details t \n" +
            "\tInner join User_details u\n" +
            "    on  t.User_Id =u.User_Id\n" +
            "    inner join Book_Details b\n" +
            "\ton t.Book_Id = b.Book_Id";

    public static final String GET_TR_PENDING =GET_TRANSACTİON_MAIN+" where t.Status=1 or t.Status=5";

    public static final String GET_TR_DELIVERY =GET_TRANSACTİON_MAIN+" where t.Status=3";

    public static final String ADD_TRANSACTION = "Insert into Transaction_Details (Tr_Id,User_id,Book_Id,tr_Date,Status)\n" +
            "values (default,:user_id,:book_id,default,:status);";

    public static final String UPDATE_TR_STATUS="update Transaction_Details set status=:status where Tr_Id = :tr_id;";

    public static final String GET_TRANSACTION_BY_ID =GET_TRANSACTİON_MAIN + " where Tr_Id= :tr_id;";

    public  static final String GET_TR_DELIVERY_BY_USER=GET_TRANSACTİON_MAIN+ " where u.user_Id=:userId and (t.Status=3 or t.Status=5) ;";

    public static final  String GET_TR_PENDING_BY_USER=GET_TRANSACTİON_MAIN+ " where t.Status=1 and u.user_Id=:userId ;";

    public  static  final  String GET_BOOK_LIST_SEARCH="select b.Book_Id ,b.Author ,b.Name,b.Language,b.No_Copies_Actual,b.No_Copies_Current,b.Category_id,b.Publication_year , c.Category_name from Book_Details b \n" +
            "             inner Join  Category_Details c on b.Category_id =c.Category_id  where b.Active = 1 and (b.Author like :keyword or b.Name like :keyword or b.Language like :keyword or c.Category_name like :keyword );";

    public static final String GET_BOOK_LIST_SEARCH_NUMERIC="select b.Book_Id ,b.Author ,b.Name,b.Language,b.No_Copies_Actual,b.No_Copies_Current,b.Category_id,b.Publication_year , c.Category_name from Book_Details b \n" +
            "                         inner Join  Category_Details c on b.Category_id =c.Category_id  where b.Active = 1 and (b.No_Copies_Actual like :keyword or b.No_Copies_Current like :keyword or b.Publication_year like :keyword );";

    public  static  final String GET_USERS_SEARCH="Select u.User_Id , u.Full_Name ,u.Username ,u.Phone ,u.Date_Of_Birthday ,u.Registration_Date ,  u.User_Role_Id,r.Role_name\n" +
            "            from User_Details u inner join  Role_Details r on u.User_role_Id  = r.Role_Id where u.Active = 1 and (u.Full_Name like :keyword or u.Username like :keyword or u.Phone like :keyword or u.Date_Of_Birthday like :keyword);";

}
