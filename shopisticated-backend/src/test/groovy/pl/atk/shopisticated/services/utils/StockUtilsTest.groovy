package pl.atk.shopisticated.services.utils

import pl.atk.shopisticated.exceptions.UnknownItemException
import pl.atk.shopisticated.exceptions.UnsufficientItemsException
import pl.atk.shopisticated.model.Item
import spock.lang.Specification

class StockUtilsTest extends Specification {
    def "should throw exception on null"() {
        when:
        StockUtils.checkNotNull(null)
        then:
        thrown(UnknownItemException)
    }

    def "should not throw exception on non-null item"() {
        given:
        def item = item()
        when:
        StockUtils.checkNotNull(item)
        then:
        notThrown(UnknownItemException)
    }

    def "should throw eception on unsufficient items"() {
        given:
        def item = item()
        when:
        StockUtils.checkAvailable(item, 6)
        then:
        thrown(UnsufficientItemsException)
    }

    def "should not throw eception on sufficient items"() {
        given:
        def item = item()
        when:
        StockUtils.checkAvailable(item, 4)
        then:
        notThrown(UnsufficientItemsException)
    }

    def item() {
        Item.builder()
                .id(UUID.randomUUID())
                .description("test")
                .name("item")
                .available(5)
                .build()
    }

}
