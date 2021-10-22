import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
         // prefix for carrying messages to clients
         registry.enableSimpleBroker("/communicator");
         // prefix for messages that are bound for methods annotated with @MessageMapping
         registry.setApplicationDestinationPrefixes("/messageBroker");
    }

    /*endpoint, enabling SockJS fallback options so that alternate transports can be used
    if WebSocket is not available*/
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("supporter");
    }
}
