package ee.tanel.veebipood.controller;

import ee.tanel.veebipood.dto.OrderRowDto;
import ee.tanel.veebipood.entity.Order;
import ee.tanel.veebipood.entity.OrderRow;
import ee.tanel.veebipood.entity.Product;
import ee.tanel.veebipood.repository.OrderRepository;
import ee.tanel.veebipood.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrderController {


    private OrderRepository orderRepository;
    private OrderService orderService;


    @GetMapping("orders")
    public List<Order> getOrders(){
        return orderRepository.findAll();
    }

    @DeleteMapping("orders/{id}")
    public List<Order> deleteOrder(@PathVariable Long id){
        orderRepository.deleteById(id); // kustutan
        return orderRepository.findAll();
    }

    // person -> autentimise tokenist
    @PostMapping("orders")
    public Order addOrder(@RequestParam Long personId, String parcelMachine, @RequestBody List<OrderRowDto> orderRows){
         return orderService.saveOrder(personId, parcelMachine, orderRows ); //siin salvestab
        //return orderRepository.findAll(); // siin on uuenenud seis
    }
}
