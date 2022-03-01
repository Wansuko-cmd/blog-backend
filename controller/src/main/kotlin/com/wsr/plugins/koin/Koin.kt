package com.wsr.plugins.koin

import DatabaseWrapper
import article.ArticleRepositoryImpl
import article.create.CreateArticleUseCase
import article.create.CreateArticleUseCaseImpl
import article.delete.DeleteArticleUseCase
import article.delete.DeleteArticleUseCaseImpl
import article.get.GetArticleUseCase
import article.get.GetArticleUseCaseImpl
import article.update.UpdateArticleUseCase
import article.update.UpdateArticleUseCaseImpl
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
        single<CreateArticleUseCase> { CreateArticleUseCaseImpl(get()) }
        single<UpdateArticleUseCase> { UpdateArticleUseCaseImpl(get()) }
        single<DeleteArticleUseCase> { DeleteArticleUseCaseImpl(get()) }

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
