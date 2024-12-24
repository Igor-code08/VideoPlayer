package com.example.videoplayer

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.VideoView

class MainActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView
    private lateinit var btnStop: Button
    private lateinit var btnNext: Button
    private lateinit var btnPrevious: Button

    // Список видеофайлов
    private val videoList = listOf(
        R.raw.video1,
        R.raw.video2
    )
    private var currentVideoIndex = 0 // Индекс текущего видео

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Инициализация элементов интерфейса
        videoView = findViewById(R.id.videoView)
        btnStop = findViewById(R.id.btnStop)
        btnNext = findViewById(R.id.btnNext)
        btnPrevious = findViewById(R.id.btnPrevious)

        // Установка первого видео
        setVideo(currentVideoIndex)

        // Логика кнопки "Остановить"
        btnStop.setOnClickListener {
            if (videoView.isPlaying) {
                videoView.pause()
            }
        }

        // Логика кнопки "Следующее видео"
        btnNext.setOnClickListener {
            currentVideoIndex = (currentVideoIndex + 1) % videoList.size
            setVideo(currentVideoIndex)
            videoView.start()
        }
        // Логика кнопки "Предыдущее видео"
        btnPrevious.setOnClickListener {
            currentVideoIndex =
                if (currentVideoIndex - 1 < 0) videoList.size - 1 else currentVideoIndex - 1
            setVideo(currentVideoIndex)
            videoView.start()
        }

        // Запуск видео по нажатию на VideoView
        videoView.setOnClickListener {
            if (!videoView.isPlaying) {
                videoView.start()
            } else {
                videoView.pause()
            }
        }
    }

    // Метод для установки видео по индексу
    private fun setVideo(index: Int) {
        val videoUri = Uri.parse("android.resource://$packageName/${videoList[index]}")
        videoView.setVideoURI(videoUri)
    }
}