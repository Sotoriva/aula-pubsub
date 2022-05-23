package br.edu.unicesumar.pubsub.amqp;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.unicesumar.pubsub.domain.Aluno;

@Component
public class RabbitStartup {

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Autowired
    // private RabbitTemplate rabbitTemplate;

    @PostConstruct
    private void adicionaTopic() {

        Queue topicTeste = new Queue("topic-joao-sotoriva", true, false, false);
        TopicExchange topicExchangeTeste = new TopicExchange("topic-exchange-teste", true, false);
        Binding bindingTeste = new Binding(topicTeste.getName(), DestinationType.QUEUE, topicExchangeTeste.getName(), "", null);
        this.amqpAdmin.declareQueue(topicTeste);
        this.amqpAdmin.declareExchange(topicExchangeTeste);
        this.amqpAdmin.declareBinding(bindingTeste);

        // this.rabbitTemplate.convertAndSend(fanoutExchangeTeste.getName(), "", "message");

    }

    @PostConstruct
    private void adicionaDirect() {

        Queue directTeste = new Queue("direct-joao-sotoriva", true, false, false);
        DirectExchange directExchangeTeste = new DirectExchange("direct-exchange-teste", true, false);
        Binding bindingTesteInfo = new Binding(directTeste.getName(), DestinationType.QUEUE, directExchangeTeste.getName(), "#.info", null);
        Binding bindingTesteWarn = new Binding(directTeste.getName(), DestinationType.QUEUE, directExchangeTeste.getName(), "#.warn", null);
        Binding bindingTesteError = new Binding(directTeste.getName(), DestinationType.QUEUE, directExchangeTeste.getName(), "#.error", null);
        Binding bindingTesteLog = new Binding(directTeste.getName(), DestinationType.QUEUE, directExchangeTeste.getName(), "log.*", null);
        this.amqpAdmin.declareQueue(directTeste);
        this.amqpAdmin.declareExchange(directExchangeTeste);
        this.amqpAdmin.declareBinding(bindingTesteInfo);

        // this.rabbitTemplate.convertAndSend(fanoutExchangeTeste.getName(), "", "message");

    }

    @PostConstruct
    private void adicionaFanout() {

        Queue filaTeste = new Queue("fila-joao-sotoriva", true, false, false);
        FanoutExchange fanoutExchangeTeste = new FanoutExchange("fanout-exchange-teste", true, false);
        Binding bindingTeste = new Binding(filaTeste.getName(), DestinationType.QUEUE, fanoutExchangeTeste.getName(), "", null);
        this.amqpAdmin.declareQueue(filaTeste);
        this.amqpAdmin.declareExchange(fanoutExchangeTeste);
        this.amqpAdmin.declareBinding(bindingTeste);

        // this.rabbitTemplate.convertAndSend(fanoutExchangeTeste.getName(), "", "message");

    }

   /* @RabbitListener(queues = "fila-teste", ackMode = "AUTO")
    private void consumerFilaTeste(Aluno aluno) {
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(aluno);
    }*/

}
