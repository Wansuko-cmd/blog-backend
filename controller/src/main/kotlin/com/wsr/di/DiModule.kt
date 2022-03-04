package com.wsr.di

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
import comment.CommentRepositoryImpl
import comment.ReplyRepositoryImpl
import comment.create.CreateCommentUseCase
import comment.create.CreateCommentUseCaseImpl
import comment.create.CreateReplyUseCase
import comment.create.CreateReplyUseCaseImpl
import comment.get.GetCommentUseCase
import comment.get.GetCommentUseCaseImpl
import dev.DevDatabase
import entities.article.ArticleRepository
import entities.comment.CommentRepository
import entities.comment.ReplyRepository
import io.ktor.application.*
import org.koin.dsl.module
import org.koin.ktor.ext.Koin

fun Application.diModule() {
    val module = module {
        /*** UseCase ***/

        //Article
        single<GetArticleUseCase> { GetArticleUseCaseImpl(get()) }
        single<CreateArticleUseCase> { CreateArticleUseCaseImpl(get()) }
        single<UpdateArticleUseCase> { UpdateArticleUseCaseImpl(get()) }
        single<DeleteArticleUseCase> { DeleteArticleUseCaseImpl(get()) }

        //Comment
        single<GetCommentUseCase> { GetCommentUseCaseImpl(get(), get()) }
        single<CreateCommentUseCase> { CreateCommentUseCaseImpl(get()) }
        single<CreateReplyUseCase> { CreateReplyUseCaseImpl(get()) }

        /*** Repository ***/

        /*** Repository ***/
        //Article
        single<ArticleRepository> { ArticleRepositoryImpl(get()) }

        //Comment
        single<CommentRepository> { CommentRepositoryImpl(get()) }
        single<ReplyRepository> { ReplyRepositoryImpl(get()) }

        /*** Database ***/

        /*** Database ***/
        single<DatabaseWrapper> { DevDatabase }
    }

    install(Koin) {
        modules(module)
    }
}
