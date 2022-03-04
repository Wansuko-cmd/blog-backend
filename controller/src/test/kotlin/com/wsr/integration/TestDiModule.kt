package com.wsr.integration

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
import entities.article.ArticleRepository
import entities.comment.CommentRepository
import entities.comment.ReplyRepository
import io.ktor.application.*
import org.koin.dsl.module
import org.koin.ktor.ext.Koin
import com.wsr.integration.testdb.TestDatabase

fun Application.testDiModule() {
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
        //Article
        single<ArticleRepository> { ArticleRepositoryImpl(get()) }

        //Comment
        single<CommentRepository> { CommentRepositoryImpl(get()) }
        single<ReplyRepository> { ReplyRepositoryImpl(get()) }

        /*** Database ***/
        single<DatabaseWrapper> { TestDatabase }
    }

    install(Koin) {
        modules(module)
    }
}
