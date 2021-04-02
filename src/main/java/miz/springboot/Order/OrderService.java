package miz.springboot.Order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
