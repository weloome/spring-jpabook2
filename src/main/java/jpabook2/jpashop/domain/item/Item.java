package jpabook2.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook2.jpashop.domain.Category;
import jpabook2.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 전략 설정
@DiscriminatorColumn()
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    // 비즈니스 로직
    public void addStock(int quantity) { // 재고 증가
        this.stockQuantity += quantity;
    }
    public void removeStock(int quantity) { // 재고 감소
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
    public void change(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = quantity;
    }
}
