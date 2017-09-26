package pl.atk.shopisticated.repositories

import pl.atk.shopisticated.model.Item
import spock.lang.Specification
import spock.lang.Unroll

class ItemRepositoryTest extends Specification {

    @Unroll
    def "should return #item on #uuid"() {
        given:
        def itemRepository = new ItemRepository()
        expect:
        itemRepository.findOne(UUID.fromString(uuid)) == item
        where:
        uuid                                   || item
        "fcfe7c54-2499-4226-a541-91cddb94f526" || new Item(
                                                    UUID.fromString("fcfe7c54-2499-4226-a541-91cddb94f526"),
                                                    "Item B",
                                                    "Lorem ipsum",
                                                    10,
                                                    "static/images/b.jpg"
                                                    )
        "fcfe7c54-2499-4226-a541-91cddb94f529" || null


    }

    def "should return correct list of items"() {
        given:
        def itemRepository = new ItemRepository()
        when:
        itemRepository.findAll()
        then:
        [ new Item(
                UUID.fromString("77433ce3-86be-4a68-af60-843bb4de076e"),
                "Item A",
                "Lorem ipsum",
                20,
                "static/images/a.jpg"
        ), new Item(
                UUID.fromString("fcfe7c54-2499-4226-a541-91cddb94f526"),
                "Item B",
                "Lorem ipsum",
                10,
                "static/images/b.jpg")
        ]
    }

    def "should return correct count"() {
        given:
        def itemRepository = new ItemRepository()
        when:
        itemRepository.count()
        then:
        2
    }

    def "should update exiting item"() {
        given:
        def itemRepository = new ItemRepository()
        def uuid = UUID.fromString("fcfe7c54-2499-4226-a541-91cddb94f526")
        def updated = new Item(
                uuid,
                "changed",
                "desc",
                30,
                "new_image.jpg")

        when:
        itemRepository.save(updated)
        then:
        itemRepository.items.find{it.id == uuid}.with {
            it.name == "changed" &&
            it.description == "desc" &&
            it.available == 30 &&
            it.image == "new_image.jpg"
        }
    }
}
