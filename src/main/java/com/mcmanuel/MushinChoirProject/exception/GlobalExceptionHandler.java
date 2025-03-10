package com.mcmanuel.MushinChoirProject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LessonNotFoundException.class)
    public ProblemDetail problemDetail(LessonNotFoundException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,ex.getMessage());
    }

    @ExceptionHandler(GradeNotFoundException.class)
    public ProblemDetail problemDetail(GradeNotFoundException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,ex.getMessage());
    }

    @ExceptionHandler(AssignmentNotFoundException.class)
    public ProblemDetail problemDetail(AssignmentNotFoundException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,ex.getMessage());
    }

    @ExceptionHandler(PasswordAndRepeatPasswordNotTheSame.class)
    public ProblemDetail problemDetail(PasswordAndRepeatPasswordNotTheSame ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,ex.getMessage());
    }

    @ExceptionHandler(UserEmailNotVerified.class)
    public ProblemDetail problemDetail(UserEmailNotVerified ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,ex.getMessage());
    }

    @ExceptionHandler(OtpNotFoundException.class)
    public ProblemDetail problemDetail(OtpNotFoundException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,ex.getMessage());
    }



    //
//    @ExceptionHandler(UserNotFoundException.class)
//    public ProblemDetail problemDetail(UserNotFoundException ex){
//        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,ex.getMessage());
//    }


    //
//    @ExceptionHandler(UserNotFoundException.class)
//    public ProblemDetail problemDetail(UserNotFoundException ex){
//        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,ex.getMessage());
//    }
}
