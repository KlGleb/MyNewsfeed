package at.gleb.mynewsfeed.di.modules

import at.gleb.mynewsfeed.di.AppScope
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import dagger.Module
import dagger.Provides
import javax.inject.Named

const val JACKSON_MAPPER = "JACKSON_MAPPER"

@Module
class JacksonMapperModule {
    @AppScope
    @Provides
    @Named(JACKSON_MAPPER)
    fun mapperKotlin(): ObjectMapper = jacksonObjectMapper().registerKotlinModule()
}