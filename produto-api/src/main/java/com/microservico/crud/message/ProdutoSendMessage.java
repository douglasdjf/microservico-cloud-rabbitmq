package com.microservico.crud.message;

import com.microservico.crud.dto.ProdutoDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdutoSendMessage {

    @Value("${produto.rabbitmq.exchange}")
    private String exchange;

    @Value("${produto.rabbitmq.routingkey}")
    private String routingkey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(ProdutoDTO produtoDTO){
        rabbitTemplate.convertAndSend(exchange,routingkey,produtoDTO);
    }

}
