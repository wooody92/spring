package dev.codesquad.jpashop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderSearch {

  private String memberName;    //회원 이름
  private OrderStatus orderStatus; //주문 상태[ORDER, CANCEL]

  public OrderSearch(String memberName, OrderStatus orderStatus) {
    this.memberName = memberName;
    this.orderStatus = orderStatus;
  }
}
