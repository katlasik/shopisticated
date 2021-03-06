package pl.atk.shopisticated.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.restdocs.payload.JsonFieldType
import pl.atk.shopisticated.model.Order
import pl.atk.shopisticated.repositories.OrderRepository

import java.time.LocalDateTime

import static org.springframework.http.MediaType.*
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class OrderControllerSpec extends AbstractControllerSpec {

    @Autowired
    OrderRepository orderRepository

    @Autowired
    ObjectMapper objectMapper

    def "should return correct list of resources"() {
        given:
        orderRepository.save(new Order(
                UUID.randomUUID(),
                UUID.fromString("fcfe7c54-2499-4226-a541-91cddb94f526"),
                "item",
                5,
                LocalDateTime.now()
        ))

        when:
        def result = mockMvc.perform(get('/orders'))
        then:
        result
                .andExpect(status().isOk())
                .andDo(document('orders/GET', preprocessResponse(prettyPrint()),
                responseFields(
                        fieldWithPath("[].id")
                                .type(JsonFieldType.STRING)
                                .description("UUID of order."),
                        fieldWithPath("[].itemId")
                                .type(JsonFieldType.STRING)
                                .description("UUID of ordered item."),
                        fieldWithPath("[].name")
                                .type(JsonFieldType.STRING)
                                .description("Name of ordered item."),
                        fieldWithPath("[].quantity")
                                .type(JsonFieldType.NUMBER)
                                .description("Number of ordered items."),
                        fieldWithPath("[].date")
                                .type(JsonFieldType.STRING)
                                .description("Date of order."),
                )
        ))
    }

    def "should add item on post"() {
        given:
        def order = new Order(
                UUID.randomUUID(),
                UUID.fromString("fcfe7c54-2499-4226-a541-91cddb94f526"),
                "item",
                5,
                LocalDateTime.now()
        )

        when:
        def result = mockMvc.perform(post('/orders')
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(order))
        )

        then:
        result
                .andExpect(status().isOk())
                .andDo(document('orders/POST', preprocessResponse(prettyPrint()),
                responseFields(
                        fieldWithPath("id")
                                .type(JsonFieldType.STRING)
                                .description("UUID of order."),
                        fieldWithPath("itemId")
                                .type(JsonFieldType.STRING)
                                .description("UUID of ordered item."),
                        fieldWithPath("name")
                                .type(JsonFieldType.STRING)
                                .description("Name of ordered item."),
                        fieldWithPath("quantity")
                                .type(JsonFieldType.NUMBER)
                                .description("Number of ordered items."),
                        fieldWithPath("date")
                                .type(JsonFieldType.STRING)
                                .description("Date of order."),
                )
        ))
    }
}
