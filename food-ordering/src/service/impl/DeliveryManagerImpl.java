package service.impl;

import enums.OrderStatus;
import model.DeliveryExecutor;
import model.Location;
import service.DeliveryExecutorSelectionStrategy;
import service.DeliveryManager;
import service.OrderManager;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DeliveryManagerImpl implements DeliveryManager {

    Map<String, DeliveryExecutor> deliveryExecutorMap;
    OrderManager orderManager;
    DeliveryExecutorSelectionStrategy deliveryExecutorSelectionStrategy;

    public DeliveryManagerImpl(Map<String, DeliveryExecutor> deliveryExecutorMap, OrderManager orderManager, DeliveryExecutorSelectionStrategy deliveryExecutorSelectionStrategy) {
        this.deliveryExecutorMap = deliveryExecutorMap;
        this.orderManager = orderManager;
        this.deliveryExecutorSelectionStrategy = deliveryExecutorSelectionStrategy;
    }

    /**
     *
     *
     * @param orderId
     * @return
     *
     *
     * 2 fallouts with original design
     * 1. you are fetching all available delivery executors ? - do we need this
     * 2. time based is calculated based on the executor distance to the user. but instead it should be close to the restaurant
     * because total_time = time to reach restaurant + time to reach user
     * 3. delivery executor migh accept or decline so what should be done in this case? - Expected to clarify during the intitial think through
     *
     * 4. Are u going to run this method in parallel? Like u have multiple order and multiple delivery executors who need to assigned.
     * DE 1 -> chennai
     * DE 2 -> delhi
     * Would it be parallel or sequential?
     * - parallel
     *
     * But there is one common notification service that sends out updates
     * So when multiple people access it in paralled ( even in database) there is design pattern that is used?
     * One resouce but lot of clients who want to access how do u handle it
     * 1. Queue -> but u want to run it in parallel
     * 2. Singleton -> Thread pool
     */
    @Override
    public DeliveryExecutor assignDeliverExecutor(String orderId) {
        // verify order
        OrderStatus orderStatus = orderManager.getOrderStatus(orderId);
        if(!orderStatus.equals(OrderStatus.COOKED)){
            throw new RuntimeException("Order is not ready  yet");
        }

        // get destination location
        Location destination = orderManager.getOrder(orderId).getDestination();

        DeliveryExecutor deliveryExecutor = deliveryExecutorSelectionStrategy.findDeliveryExecutor(findAvailableExecutors(), destination);

        return deliveryExecutor;
    }


    private List<DeliveryExecutor> findAvailableExecutors(){
        return deliveryExecutorMap.values()
                .stream()
                .filter(deliveryExecutor ->  deliveryExecutor.getAvailable())
                .collect(Collectors.toList());
    }
}
