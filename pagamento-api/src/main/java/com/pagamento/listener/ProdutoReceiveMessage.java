package com.pagamento.listener;

import com.pagamento.domain.model.Produto;
import com.pagamento.domain.repository.ProdutoRepository;
import com.pagamento.dto.ProdutoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ProdutoReceiveMessage {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @RabbitListener(queues = {"${pagamento.rabbitmq.queue}"})
    public void receive(@Payload ProdutoDTO produtoDTO){
        produtoRepository.save(modelMapper.map(produtoDTO, Produto.class));
    }
}
