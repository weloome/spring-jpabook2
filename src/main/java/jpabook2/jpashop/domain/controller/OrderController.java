package jpabook2.jpashop.domain.controller;

import jpabook2.jpashop.domain.Member;
import jpabook2.jpashop.domain.item.Item;
import jpabook2.jpashop.domain.service.ItemService;
import jpabook2.jpashop.domain.service.MemberService;
import jpabook2.jpashop.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    public final OrderService orderService;
    public final MemberService memberService;
    public final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model) {

        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(@RequestParam("memberId") Long memberId, @RequestParam("itemId") Long itemId, @RequestParam("count") int count) {
        orderService.order(memberId, itemId, count);
        return "redirect:orders";
    }
}
