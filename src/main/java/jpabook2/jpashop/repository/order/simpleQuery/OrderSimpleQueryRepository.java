package jpabook2.jpashop.repository.order.simpleQuery;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {

    private final EntityManager em;

    public List<OrderSimpleQueryDto> findOrderDtos() {
        return em.createQuery(
                "select new jpabook2.jpashop.repository.order.simpleQuery.OrderSimpleQueryDto(o.id, m.name, o.orderDate, o.status, d.address) o " +
                        "from Order o " +
                        "join o.member m " +
                        "join o.delivery d", OrderSimpleQueryDto.class
        ).getResultList();
    }
}
