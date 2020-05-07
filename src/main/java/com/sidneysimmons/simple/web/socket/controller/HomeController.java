package com.sidneysimmons.simple.web.socket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home controller for home related endpoints.
 * 
 * @author Sidney Simmons
 */
@Controller
@RequestMapping(value = "")
public class HomeController {

    /**
     * Return the home page.
     * 
     * @return the home page
     */
    @GetMapping(value = "")
    public String getHome() {
        return ViewNames.HOME;
    }

}
