package jpabook.jpashop;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.item.Book;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InitDb {
  
  private final InitService initService;

  @PostConstruct
  public void init() {
    initService.dbInit1();
    initService.dbInit2();
  }

  @Component
  @Transactional
  @RequiredArgsConstructor
  static class InitService {
    private final EntityManager em;
    public void dbInit1() {
      Member member = createMember("userA", "서울", "동작구", "1111");
      em.persist(member);

      Book book1 = createBook("JPA1 BOOK", 10000, 100);
      Book book2 = createBook("JPA2 BOOK", 20000, 100);

      OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
      OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

      Delivery delivery = new Delivery();
      delivery.setAddress(member.getAddress());
      Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
      em.persist(order);
    }

    public void dbInit2() {
      Member member = createMember("userB", "부산", "서래포구", "234234");

      Book book1 = createBook("SPRING1 BOOK", 10000, 100);
      Book book2 = createBook("SPRING2 BOOK", 20000, 100);

      OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
      OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

      Delivery delivery = new Delivery();
      delivery.setAddress(member.getAddress());
      Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
      em.persist(order);
    }

    private Book createBook(String name, int price, int quantity) {
      Book book1 = new Book();
      book1.setName(name);
      book1.setPrice(price);
      book1.setStockQuantity(quantity);
      em.persist(book1);
      return book1;
    }



    private Member createMember(String name, String city, String street, String zipcode) {
      Member member = new Member();
      member.setName(name);
      member.setAddress(new Address(city, street, zipcode));
      em.persist(member);
      return member;
    }

  }
}
