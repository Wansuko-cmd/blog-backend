package com.wsr.plugins.koin

import core.ArticleService
import core.ArticleServiceImpl
import db.builder.DevH2
import db.builder.H2
import dsl.ArticleDsl
import dsl.ArticleDslImpl
import io.ktor.application.*
import org.koin.dsl.module
import org.koin.ktor.ext.Koin
import repository.ArticleRepository
import repository.ArticleRepositoryImpl

fun Application.configureKoin() {

    val h2 = H2(DevH2)

    val module = module {
        factory<ArticleService> { ArticleServiceImpl(get()) }
        factory<ArticleRepository> { ArticleRepositoryImpl(get(), h2, DevH2) }
        factory<ArticleDsl> { ArticleDslImpl() }
    }

    install(Koin) {
        modules(module)
    }
}
