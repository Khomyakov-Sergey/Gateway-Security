package ru.clevertec.simpleapplication.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.clevertec.simpleapplication.entity.User;

@FeignClient(value = "registration", url = "${api.service.url}")
public interface ApiFeignClient {

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    String  registerUser(@RequestBody User user);
}
