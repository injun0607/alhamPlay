package kr.alham.playground.loader

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.stereotype.Component

@Component
class ItemRecipeLoader {


    private val mapper = jacksonObjectMapper()


}