package org.freeswitch.esl.client;

import com.google.common.base.Throwables;
import org.freeswitch.esl.client.inbound.Client;
import org.freeswitch.esl.client.inbound.IEslEventListener;
import org.freeswitch.esl.client.internal.Context;
import org.freeswitch.esl.client.internal.IModEslApi.EventFormat;
import org.freeswitch.esl.client.transport.event.EslEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class ClientExample {
    private static final Logger L = LoggerFactory.getLogger(ClientExample.class);

    public static void main(String[] args) {
        try {

            String password = "ClueCon";

            Client client = new Client();

            client.addEventListener((ctx, event) -> {
                String ename = event.getEventName();
                if ("CUSTOM".equals(ename)) {
                    String res = event.getEventHeaders().get("ASR-Response");
                    L.info("Asr-Res:" + res);
                }
            });
            // ClueConSmartPhone@lieluobo
            client.connect(new InetSocketAddress("172.20.0.14", 8021), password, 10);
            client.setEventSubscriptions(EventFormat.PLAIN, "all");

        } catch (Throwable t) {
            Throwables.propagate(t);
        }
    }
}
