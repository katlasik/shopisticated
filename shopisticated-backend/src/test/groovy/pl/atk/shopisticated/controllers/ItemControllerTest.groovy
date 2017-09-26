package pl.atk.shopisticated.controllers

import org.springframework.restdocs.payload.JsonFieldType

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class ItemControllerTest extends AbstractControllerSpec {


    def "should return correct list of resources"() {
        when:
        def result = mockMvc.perform(get('/items/GET'))
        then:
        result
                .andExpect(status().isOk())
                .andDo(document('items', preprocessResponse(prettyPrint()),
                responseFields(
                        fieldWithPath("[].id")
                                .type(JsonFieldType.STRING)
                                .description("UUID of item."),
                        fieldWithPath("[].name")
                                .type(JsonFieldType.STRING)
                                .description("Name of item."),
                        fieldWithPath("[].description")
                                .type(JsonFieldType.STRING)
                                .description("Description of item."),
                        fieldWithPath("[].image")
                                .type(JsonFieldType.STRING)
                                .description("Image of item."),
                        fieldWithPath("[].available")
                                .type(JsonFieldType.NUMBER)
                                .description("Number of available items."),
                )
            ))
    }
}
