package kr.co.kshproject.webDemo.Common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.SQLException;
@ControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    private DataSource dataSource;

    @ExceptionHandler(SQLException.class)
    public String handleSQLException(HttpServletRequest request, Exception ex){
        // JDBC 연결이 끊겼을 때 다시 연결하는 코드를 작성합니다.
        // dataSource로부터 Connection 객체를 얻어옴
        return "error";
    }
}
