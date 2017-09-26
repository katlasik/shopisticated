package pl.atk.shopisticated.services

import pl.atk.shopisticated.exceptions.UnknownItemException
import pl.atk.shopisticated.exceptions.UnsufficientItemsException
import pl.atk.shopisticated.model.Item
import pl.atk.shopisticated.model.Order
import pl.atk.shopisticated.repositories.ItemRepository
import pl.atk.shopisticated.repositories.OrderRepository
import spock.lang.Specification

import java.time.LocalDate
import java.time.LocalDateTime

class OrderServiceTest extends Specification {

    def "should throw exception on unknown item"() {
        given:
        def service = new OrderService(new ItemRepository(), new OrderRepository())
        def order = Order.builder()
            .id(UUID.randomUUID())
            .itemId(UUID.randomUUID())
            .quantity(4)
            .date(LocalDateTime.now())
            .build()
        when:
        def item = service.purchase(order)
        then:
        thrown(UnknownItemException)
    }


    def "should throw exception on unsufficient items"() {
        given:
        def service = new OrderService(new ItemRepository(), new OrderRepository())
        def order = Order.builder()
                .id(UUID.randomUUID())
                .itemId(UUID.fromString("77433ce3-86be-4a68-af60-843bb4de076e"))
                .quantity(22)
                .date(LocalDateTime.now())
                .build()
        when:
        def item = service.purchase(order)
        then:
        thrown(UnsufficientItemsException)
    }

    def "should save new order and update item on correct purchase"() {
        given:
        def itemService = Spy(ItemRepository)
        def orderService = Spy(OrderRepository)
        def service = new OrderService(itemService, orderService)
        def order = Order.builder()
                .id(UUID.randomUUID())
                .itemId(UUID.fromString("77433ce3-86be-4a68-af60-843bb4de076e"))
                .quantity(10)
                .date(LocalDateTime.now())
                .build()
        when:
        def item = service.purchase(order)
        then:
        1 * itemService.save(new Item(
                UUID.fromString("77433ce3-86be-4a68-af60-843bb4de076e"),
                "Item A",
                "Lorem ipsum",
                10,
                'static/images/a.jpg'))

        1* itemService.findOne(UUID.fromString("77433ce3-86be-4a68-af60-843bb4de076e"))

        1 * orderService.save(order)

    }

}
