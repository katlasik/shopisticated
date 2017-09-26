package pl.atk.shopisticated.repositories

import pl.atk.shopisticated.model.Item
import pl.atk.shopisticated.model.Order
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDateTime

class OrderRepositoryTest extends Specification {

    def "should save order"() {
        given:
        def orderRepository = new OrderRepository()
        def order = Order.builder()
                .id(UUID.randomUUID())
                .itemId(UUID.fromString("77433ce3-86be-4a68-af60-843bb4de076e"))
                .quantity(10)
                .date(LocalDateTime.now())
                .build()
        when:
        orderRepository.save(order)
        then:
        orderRepository.orders.get(0) == order
    }

    def "should return correct count"() {
        given:
        def orderRepository = new OrderRepository()
        orderRepository.save(Order.builder()
                .id(UUID.randomUUID())
                .itemId(UUID.fromString("77433ce3-86be-4a68-af60-843bb4de076e"))
                .quantity(10)
                .date(LocalDateTime.now())
                .build())
        orderRepository.save(Order.builder()
                .id(UUID.randomUUID())
                .itemId(UUID.fromString("77433ce3-86be-4a68-af60-843bb4de076e"))
                .quantity(10)
                .date(LocalDateTime.now())
                .build())
        when:
        orderRepository.count()
        then:
        2
    }

    @Unroll
    def "should return #item on #uuid"() {
        given:
        def orderRepository = new OrderRepository()
        def orders = [
                Order.builder()
                        .id(UUID.fromString("77433ce3-86be-4a68-af60-843bb4de076e"))
                        .itemId(UUID.fromString("77433ce3-86be-4a68-af60-843bb4de076c"))
                        .quantity(10)
                        .build(),
                Order.builder()
                        .id(UUID.fromString("77433ce3-86be-4a68-af60-843bb4de076a"))
                        .itemId(UUID.randomUUID())
                        .quantity(10)
                        .date(LocalDateTime.now())
                        .build()
        ]
        orderRepository.orders.addAll(orders)
        expect:
        orderRepository.findOne(UUID.fromString(uuid)) == item
        where:
        uuid                                   || item
        "77433ce3-86be-4a68-af60-843bb4de076e" ||  Order.builder()
                                                    .id(UUID.fromString("77433ce3-86be-4a68-af60-843bb4de076e"))
                                                    .itemId(UUID.fromString("77433ce3-86be-4a68-af60-843bb4de076c"))
                                                    .quantity(10)
                                                    .build()
        "77433ce3-86be-4a68-af60-843bb4de0761" || null


    }

    def "should return correct list"() {
        given:
        def orderRepository = new OrderRepository()
        def orders = [
                Order.builder()
                        .id(UUID.randomUUID())
                        .itemId(UUID.fromString("77433ce3-86be-4a68-af60-843bb4de076e"))
                        .quantity(10)
                        .date(LocalDateTime.now())
                        .build(),
                Order.builder()
                        .id(UUID.randomUUID())
                        .itemId(UUID.fromString("77433ce3-86be-4a68-af60-843bb4de076e"))
                        .quantity(10)
                        .date(LocalDateTime.now())
                        .build()
        ]
        orderRepository.orders.addAll(orders)
        when:
        orderRepository.findAll()
        then:
        orders
    }





}
