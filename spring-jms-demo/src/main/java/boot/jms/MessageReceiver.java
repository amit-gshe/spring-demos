package boot.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {
	@JmsListener(destination = "hello-broker")
	public void onReceive(String message){
		System.out.println(message);
	}
}
