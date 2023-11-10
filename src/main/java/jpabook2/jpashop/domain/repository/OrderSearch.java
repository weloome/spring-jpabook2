package jpabook2.jpashop.domain.repository;

import jpabook2.jpashop.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus;
}
