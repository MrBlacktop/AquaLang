package com.example.aqualang.di

import com.example.aqualang.education.course.CourseFragment
import com.example.aqualang.education.coursesList.CoursesListFragment
import com.example.aqualang.education.exercises.ExerciseComponent
import com.example.aqualang.education.exercises.SingleOptionExerciseFragment
import com.example.aqualang.education.exercises.controlFragment.ExerciseControlFragment
import com.example.aqualang.education.glossary.GlossaryFragment
import com.example.aqualang.education.glossary.word.WordFragment
import com.example.aqualang.education.lesson.LessonFragment
import com.example.aqualang.education.lessonList.LessonListFragment
import com.example.aqualang.login.LoginComponent
import com.example.aqualang.login.login.LoginFragment
import com.example.aqualang.login.registration.RegistrationFragment
import com.example.aqualang.user.UserFragment
import com.example.data.database.DatabaseModule
import com.example.data.network.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class, DatabaseModule::class, InteractorModule::class, RepositoryModule::class, AppSubComponents::class])
@Singleton
interface AppComponent {
    fun loginComponent(): LoginComponent.Factory
    fun exerciseComponent(): ExerciseComponent.Factory
    fun inject(coursesListFragment: CoursesListFragment)
    fun inject(courseFragment: CourseFragment)
    fun inject(userFragment: UserFragment)
    fun inject(lessonListFragment: LessonListFragment)
    fun inject(lessonFragment: LessonFragment)
    fun inject(glossaryFragment: GlossaryFragment)
    fun inject(wordFragment: WordFragment)
}