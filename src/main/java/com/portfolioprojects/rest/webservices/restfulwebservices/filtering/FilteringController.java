package com.portfolioprojects.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue filtering() {
        SomeBean someBean = new SomeBean("value1", "value2", "value3");
        return setFilters(someBean, "field1", "field3", "");
    }

    @GetMapping("/filtering-list") //return field2 and field3 only
    public MappingJacksonValue filteringList() {
        List<SomeBean> someBeans = Arrays.asList(new SomeBean("abc", "def", "ghi"), new SomeBean("jkl", "mno", "pqr"), new SomeBean("stu", "vwx", "yz1"));
        return setFilters(someBeans, "field2", "field3", "");
    }

    //I extracted the repeating codes in this method to reduce boilerplate code
    public <T> MappingJacksonValue setFilters(T t, String field1, String field2, String field3) {
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(t);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(field1, field2, field3);
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }
}
