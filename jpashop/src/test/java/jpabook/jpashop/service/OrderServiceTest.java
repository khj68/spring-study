package jpabook.jpashop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;

@SpringBootTest
@Transactional
public class OrderServiceTest {

  @Autowired EntityManager em;
  @Autowired OrderService orderService;
  @Autowired OrderRepository orderRepository;
  
  @Test
  public void 상품주문() throws Exception {
    //given
    Member member = createMember();
    Item book = getBook("시골 JPA", 10000, 10);
    int orderCount = 2;
    
    //when
    Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
    

    //then
    Order getOrder = orderRepository.findOne(orderId);

    assertEquals(OrderStatus.ORDER, getOrder.getStatus(), "상품 주문시 상태는 ORDER");
    assertEquals(1, getOrder.getOrderItems().size(), "주문한 상품 종류 수가 정확해야 한다.");
    assertEquals(10000 * orderCount, getOrder.getTotalPrice(), "주문 가격은 가격 * 수량이다.");
    assertEquals(8, book.getStockQuantity(), "주문 수량만큼 재고가 줄어야 한다.");
  }

  @Test
  public void 주문취소() throws Exception {
    //given
    Member member = createMember();
    Item book = getBook("시골 JPA", 10000, 10);

    int orderCount = 2;

    Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

    //when
    orderService.cancleOrder(orderId);

    //then
    Order getOrder = orderRepository.findOne(orderId);

    assertEquals(OrderStatus.CANCEL, getOrder.getStatus(), "주문 취소시 상태는 cancel이다.");
    assertEquals(10, book.getStockQuantity(), "주문이 취소된 상품은 그만큼 재고가 증가해야 한다.");
  }
  
  @Test
  public void 상품주문_재고수량초과() throws Exception {
    //given
    Member member = createMember();
    Item item = getBook("시골 JPA", 10000, 10);
    
    int orderCount = 11;
    
    //when
    // orderService.order(member.getId(), item.getId(), orderCount);

    //then
    assertThrows(NotEnoughStockException.class, () -> orderService.order(member.getId(), item.getId(), orderCount));
  }

  private Item getBook(String name, int orderPrice, int stockQuantity) {
    Item book = new Book();
    book.setName(name);
    book.setPrice(orderPrice);
    book.setStockQuantity(stockQuantity);
    em.persist(book);
    return book;
  }

  private Member createMember() {
    Member member = new Member();
    member.setName("회원1");
    member.setAddress(new Address("서울", "강가", "123-123"));
    em.persist(member);
    return member;
  }
}
