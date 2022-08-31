package co.develhope.apiInterceptormiddleware02exercise.interceptors;

import co.develhope.apiInterceptormiddleware02exercise.entities.Month;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class MonthInterceptor implements HandlerInterceptor {

    public static List<Month> months = new ArrayList<>(Arrays.asList(
            new Month(1,"January","Gennaio","Januar"),
            new Month(2,"February","Febbraio","Februar"),
            new Month(3,"March","Marzo","Marsch"),
            new Month(4,"April","Aprile","April"),
            new Month(5,"May","Maggio","Kann"),
            new Month(6,"June","Giugno","Juni")
    ));

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String monthNumberString = request.getHeader("monthNumber");
        if (monthNumberString == null || monthNumberString.isEmpty()){
            response.setStatus(400);
        }else{
            int monthNumber = Integer.parseInt(monthNumberString);
            Optional<Month> month = months.stream().filter(SingleMonth ->{
                return SingleMonth.getMonthNumber() == monthNumber;
            }).findAny();
            if (month.isPresent()){
                request.setAttribute("MonthInterceptor-month",month.get());
            }else{
                request.setAttribute("MonthInterceptor-month", new Month(0,"nope","nope","nope"));
            }
            response.setStatus(200);
        }
        return true;
    }
}
