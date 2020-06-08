package com.example.aqualang.education.exercises

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.example.aqualang.R

class ExerciseActivity : AppCompatActivity() {
     var lessonId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        lessonId = intent.getIntExtra(LESSON_ID,0)

    }

    companion object {
        private const val LESSON_ID = "LESSON_ID"

        fun createBundleWithId(lessonId: Int): Bundle {
            return bundleOf(LESSON_ID to lessonId)
        }
    }
}