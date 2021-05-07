package com.library.management;
import com.library.management.model.Book;
import com.library.management.model.Transaction;
import com.library.management.model.User;
import com.library.management.repository.Inter.BookRepository;
import com.library.management.repository.Inter.TransactionRepository;
import com.library.management.repository.Inter.UserRepository;
import com.library.management.service.Inter.BookService;
import com.library.management.util.DataConverter;
import com.library.management.util.NumericChecker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ManagementApplicationTests {

    @Autowired
    public BookRepository bookRepository;

    @Autowired
    public BookService bookService;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public DataConverter dataConverter;

    @Autowired
    public TransactionRepository transactionRepository;

    @Test
    void contextLoads() {

        try {

            System.out.println( bookRepository.getBookSearch("Niz").toString());

//            Transaction transaction = new Transaction();
//
//            Book book = new Book();
//            User user= new User();
//            book.setId(1L);
//            user.setUserId(1L);
//            transaction.setStatus(1L);
//            transaction.setBook(book);
//            transaction.setUser(user);
//            System.out.println(transactionRepository.getDeliveryTransactionByUserId(4L));

//            User user = new User();
//            UserRole userRole= new UserRole();
//            user.setUserId(9L);
//        user.setFullname("User12");
//        user.setUsername("UsertestUpdate");
//        user.setPassword("1");
//        user.setPhone("12387896");
//        user.setDOB(dataConverter.stringToDate("1997-12-11"));
//        userRole.setRoleId(1);
//        user.setUserRole(userRole);
//        System.out.println( "update  user =+++ "+userRepository.updateUser(user));
        } catch (Exception e) {
            e.printStackTrace();

        }


//            User user=  userRepository.loginUser("User","1");
//            BookCategory bookCategory= new BookCategory();
//            Book bookDao = new Book();
//            Book book = new Book();
//            bookDao=bookRepository.getBookById(2L);
//            System.out.println("first "+bookDao.toString());
//            bookCategory.setId(3);
//            book.setId(2);
//            book.setName("Mirze");
//            book.setLanguage("English ");
//            book.setPublicationyear(2005);
//            book.setBookCategory(bookCategory);
//            book.setNoCopiesActual(88);
//            book.setNoCopiesCurrent(82);
//            System.out.println("----------- "+bookRepository.uptadeBook(book));
//            System.out.println("Result  "+bookRepository.getBookById(2L));
//            bookRepository.addBook(book);
    }

}
