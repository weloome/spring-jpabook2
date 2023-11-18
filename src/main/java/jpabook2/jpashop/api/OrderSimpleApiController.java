package jpabook2.jpashop.api;

import jpabook2.jpashop.domain.Address;
import jpabook2.jpashop.domain.Order;
import jpabook2.jpashop.domain.OrderStatus;
import jpabook2.jpashop.domain.repository.OrderRepository;
import jpabook2.jpashop.domain.repository.OrderSearch;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/*
* xToOne(MenyToOne, OneToOne)
* Order
* Order -> Member
* Order -> Delivery
* */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAll(new OrderSearch()); // 무한루프 발생 (양방향 연관관계 문제)
        for (Order order : all) {
            order.getMember().getName(); // Lazy 강제 초기화
            order.getDelivery().getAddress(); // Lazy 강제 초기화
        }
        return all;
    }

    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2() {
        return orderRepository.findAll(new OrderSearch()).stream() // ORDER 2개 가져옴 :: N + 1 -> 1 + 회원N(2) + 배송N(2)
                .map(SimpleOrderDto::new) // 2번 돌면서 member, delivery 각각 조회
                .collect(Collectors.toList()); // order, member, delivery - 3개 테이블 끌고옴
        /*쿼리가 총 1 + N + N번 실행된다
        * order 조회 1번(order 조회 결과 수가 N이 된다)
        * order -> Member 지연 로딩 조회 N번
        * order -> delivery 지연 로딩 조회 N번*/
    }

    @Data
    static class SimpleOrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName(); // Lazy 초기화
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress(); // Lazy 초기화
        }
    }
}
