package com.portfolioprojects.rest.webservices.restfulwebservices.helloworld;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

//expose a rest api
@RestController
public class HelloWorldController {
    private final MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    //    give specific url to rest-api
//    return "Hello World"


    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("HelloWorld");
    }

    @GetMapping("/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean(
                String.format("%s%s", "HelloWorld ", name));
    }

    //    For internationalization
//    1: we need to define the languages somewhere in the code
//    2: write code to pick those values up
    /*Example:
     * 'en' - Good morning
     * 'nl' - Goedemorge
     * 'fr'- Bonjour
     * 'de' - Guten Morgen
     * */
    @GetMapping("/hello-world-internationalized")
    public String helloWorldI18N() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
//        return "Hello World V2";
    }

}
