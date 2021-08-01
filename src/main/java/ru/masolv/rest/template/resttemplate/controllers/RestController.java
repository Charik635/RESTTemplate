package ru.masolv.rest.template.resttemplate.controllers;

import org.springframework.http.*;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.web.client.RestTemplate;


public class RestController {
    private RestTemplate restTemplate = new RestTemplate();
    private static final String URL = "http://91.241.64.178:7081/api/users";
    String cookie;
    private String one;
    private String two;
    private String three;

    private ru.masolv.rest.template.resttemplate.controllers.User user = new ru.masolv.rest.template.resttemplate.controllers.User( 3L,"James","Brown", (byte) 5);
    private ru.masolv.rest.template.resttemplate.controllers.User user1 = new ru.masolv.rest.template.resttemplate.controllers.User( 3L,"Thomas","Shelby", (byte) 5);
    public void getUsers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);
 cookie = responseEntity.getHeaders().getFirst("set-cookie");




    }
    public void saveUser(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.COOKIE,cookie);

        HttpEntity<ru.masolv.rest.template.resttemplate.controllers.User> entity = new HttpEntity<>(user,httpHeaders);
    ResponseEntity<String> responseEntity = restTemplate.exchange(URL,HttpMethod.POST,entity,String.class);
   one = responseEntity.getBody();

    }
    public void updateUser(){

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.set(HttpHeaders.COOKIE,cookie);
        HttpEntity<ru.masolv.rest.template.resttemplate.controllers.User> entity = new HttpEntity<>(user1,httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL,HttpMethod.PUT,entity,String.class);
        two = responseEntity.getBody();
    }
    public void deleteUser(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.COOKIE,cookie);
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL + "/3",HttpMethod.DELETE,entity,String.class);
        three = responseEntity.getBody();

    }

    public static void main(String[] args) {
        RestController restController = new RestController();
        StringBuilder stringBuilder = new StringBuilder();
        restController.getUsers();
        restController.saveUser();
        restController.updateUser();
        restController.deleteUser();
        stringBuilder.append(restController.one);
        stringBuilder.append(restController.two);
        stringBuilder.append(restController.three);
        System.out.println(stringBuilder);

    }

}
