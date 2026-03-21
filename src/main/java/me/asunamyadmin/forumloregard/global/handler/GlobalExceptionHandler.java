package me.asunamyadmin.forumloregard.global.handler;

import me.asunamyadmin.forumloregard.category.exception.CategoryNotFoundException;
import me.asunamyadmin.forumloregard.forum_profile.exception.ProfileNofFoundException;
import me.asunamyadmin.forumloregard.topic.exception.TopicNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CategoryNotFoundException.class, TopicNotFoundException.class, ProfileNofFoundException.class})
    public ModelAndView notFoundExceptionHandler() {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("status", 404);
        mav.setStatus(HttpStatus.NOT_FOUND);
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView globalExceptionHandler() {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("status", 500);
        mav.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return mav;
    }
}
