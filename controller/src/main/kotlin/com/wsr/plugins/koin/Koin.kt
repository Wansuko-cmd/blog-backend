package com.wsr.plugins.koin

import DatabaseWrapper
import article.ArticleRepositoryImpl
import article.get.GetArticleUseCase
import article.get.GetArticleUseCaseImpl
import dev.DevDatabase
import entities.article.ArticleRepository
import io.ktor.application.*
import org.koin.dsl.module
import org.koin.ktor.ext.Koin

fun Application.configureKoin() {

    val module = module {
        /*** UseCase ***/
        //Article
        single<GetArticleUseCase> { GetArticleUseCaseImpl(get()) }

        /*** Repository ***/
        //Article
        single<ArticleRepository> { ArticleRepositoryImpl(get()) }

        /*** Database ***/
        single<DatabaseWrapper> { DevDatabase }
    }

    install(Koin) {
        modules(module)
    }
}
