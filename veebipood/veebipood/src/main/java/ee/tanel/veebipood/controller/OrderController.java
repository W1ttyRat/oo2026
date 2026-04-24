package ee.tanel.veebipood.controller;

import ee.tanel.veebipood.dto.OrderRowDto;
import ee.tanel.veebipood.dto.ParcelMachine;
import ee.tanel.veebipood.entity.Order;
import ee.tanel.veebipood.entity.OrderRow;
import ee.tanel.veebipood.entity.Product;
import ee.tanel.veebipood.repository.OrderRepository;
import ee.tanel.veebipood.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderController {


    private OrderRepository orderRepository;
    private OrderService orderService;
    private RestTemplate restTemplate = new RestTemplate();


    @GetMapping("parcelmachines")
    public List<ParcelMachine> getParcelMachines(@RequestParam String country) {
        String url = "https://www.omniva.ee/locations.json";
        ParcelMachine[] response = restTemplate.exchange(url, HttpMethod.GET, null, ParcelMachine[].class).getBody();
        return Arrays.stream(response).filter(e -> e.getA0_name().equals(country.toUpperCase())).toList();
    }

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
