package com.sofka.rabbitmq.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/edificio/apartamento/")
public class rabbitMQWebController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping (value = "/enviarCorrespondenciaImpar")
    public String producer(
            @RequestParam("apartamento") String apartamento,
            @RequestParam("direccion") String direccion,
            @RequestParam("correspondecia") String correspondecia
    ){
        amqpTemplate.convertAndSend(apartamento,direccion,correspondecia);
        return "Correspondecia enviada a la direccion seleccionada !!";

    }



}
