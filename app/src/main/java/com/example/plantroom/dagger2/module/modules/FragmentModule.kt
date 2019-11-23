package com.example.plantroom.dagger2.module.modules

import com.example.plantroom.view.quiz.ui.QuizFragment
import com.example.plantroom.view.sign_in.create_account.ui.CreateAccountFragment
import com.example.plantroom.view.sign_in.sign_in.ui.SignInFragment
import com.example.plantroom.view.welcome.ui.WelcomeFragment
import dagger.Binds
import dagger.Module

@Module
interface FragmentModule {

    @Binds
    fun bindWelcomeFragment(welcomeFragment: WelcomeFragment) : WelcomeFragment

    @Binds
    fun bindCreateAccountFragment(createAccountFragment: CreateAccountFragment) : CreateAccountFragment

    @Binds
    fun bindSignInFragment(signInFragment: SignInFragment) : SignInFragment

    @Binds
    fun bindQuizFragment(quizFragment: QuizFragment) : QuizFragment
}
