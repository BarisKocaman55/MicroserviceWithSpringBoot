package com.rest.webservices.restful_web_services.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/filter")
public class FilteringController {

    @GetMapping
    public SomeBean filtering() {
        return new SomeBean("Baris", "Kocaman", "123");
    }

    @GetMapping("/list")
    public List<SomeBean> filteringList() {
        return Arrays.asList(new SomeBean("Baris", "Kocaman", "123"),
                             new SomeBean("Elifsu", "Kocaman", "5454"));
    }

    @GetMapping("dynamic1")
    public MappingJacksonValue filteringDynamic1() {
        SomeBean2 someBean2 = new SomeBean2("Baris", "Kocaman", "123");

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean2);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("name", "password");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }

    @GetMapping("dynamic2")
    public MappingJacksonValue filteringDynamic2() {
        List<SomeBean2> someBean = Arrays.asList(new SomeBean2("Baris", "Kocaman", "123"),
                                                 new SomeBean2("Elifsu", "Kocaman", "5454"));

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("surname", "password");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }
}
