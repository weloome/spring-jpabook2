package jpabook2.jpashop.domain.controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "회원 이름은 필수입니다") // 필수값 validation
    private String name;

    private String city;
    private String street;
    private String zipcode;
}
