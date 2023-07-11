package c.e.security.service

import c.e.security.entity.Order
import c.e.security.repository.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class OrderService {

    @Autowired
    private lateinit var orderRepository: OrderRepository

    fun addOrder(order: Order): Order {
        return orderRepository.save(order)
    }


    fun getAllOrders(): List<Order> {
        return orderRepository.findAll()
    }

    fun getOrdersByCustomerId(customerId:Int):ArrayList<Order>{
        return orderRepository.findOrdersBycustomerId(customerId)
    }

    fun deleteOrderById(orderId:Int){
        orderRepository.deleteById(orderId)
    }

    fun deleteAllOrdersByCustomerId(customerId: Int) {
        orderRepository.deleteAllOrdersBycustomerId(customerId)

    }


}