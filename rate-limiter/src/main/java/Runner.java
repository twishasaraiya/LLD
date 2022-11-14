import model.Request;
import services.GatewayService;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Runner {

    public static void main(String[] args) throws InterruptedException {
        GatewayService gateway = new GatewayService();

        Request request = new Request("request-123");

        ExecutorService pool = Executors.newFixedThreadPool(12);

        for(int i=0; i<12; i++){
            pool.submit(() -> gateway.processRequest(request));
        }
        pool.shutdown();
    }
}
