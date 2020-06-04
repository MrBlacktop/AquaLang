package com.example.aqualang.education.course

import androidx.lifecycle.ViewModel
import com.example.domain.interactors.CourseInteractor

class CourseViewModel(courseInteractor: CourseInteractor, courseId:Int) : ViewModel() {
    val course = courseInteractor.getCourse(courseId)
}