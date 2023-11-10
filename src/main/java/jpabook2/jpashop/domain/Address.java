package jpabook2.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable // 내장 타입 선언
@Getter // 값 타입은 불변 객체로 설계
public class Address {
    private String city;
    private String street;
    private String zipcode;

    protected Address() { } // 기본 생성자는 public 보단 protected 가 더 안전함

    public Address(String city, String street, String zipcode) {
        // 생성자에서 값을 모두 초기화하여 변경 불가능한 클래스로 만듦
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
