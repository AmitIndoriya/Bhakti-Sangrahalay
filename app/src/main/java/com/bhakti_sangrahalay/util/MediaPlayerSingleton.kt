package com.bhakti_sangrahalay.util

import android.media.MediaPlayer
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.bhakti_sangrahalay.MyApplication
import com.bhakti_sangrahalay.contansts.MusicPlayerConstant
import com.bhakti_sangrahalay.model.MusicPlayStausModel
import com.bhakti_sangrahalay.model.MusicSeekBarProgress
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File

object MediaPlayerSingleton {
    var mediaPlayer: MediaPlayer
    var currentPlayingSongId: Int = 0
    val playStatusLiveData = MutableLiveData<MusicPlayStausModel>()
    val  musicSeekBarProgressLiveData = MutableLiveData<MusicSeekBarProgress>()


    init {
        Utility.printLog("Singleton initialized.")
        mediaPlayer = MediaPlayer()
    }

    @DelicateCoroutinesApi
    fun doActionOnEvent(action: Int, songId: Int) {
        when (action) {
            MusicPlayerConstant.PLAY_SONG -> {
                playSong(songId)
            }
            MusicPlayerConstant.PAUSE_SONG -> {
                pauseSong()
            }
            MusicPlayerConstant.NEXT_SONG -> {
                playSong(songId)
            }
            MusicPlayerConstant.PREVIOUS_SONG -> {
                playSong(songId)
            }
        }
    }

    @DelicateCoroutinesApi
    fun playSong(songId: Int) {
        val url = MyApplication.applicationContext().dataHoler.hashMap[songId]?.localSaveUrl
        if (url != null) {
            val file = File(url)
            if (file.exists()) {
                val uri = Uri.fromFile(file)
                if (currentPlayingSongId != songId && mediaPlayer.isPlaying()) {
                    mediaPlayer.stop()
                    mediaPlayer = MediaPlayer.create(MyApplication.applicationContext(), uri)
                } else if (!mediaPlayer.isPlaying() && currentPlayingSongId != songId) {
                    mediaPlayer = MediaPlayer.create(MyApplication.applicationContext(), uri)
                }
                mediaPlayer.start()
                playStatusLiveData.value = MusicPlayStausModel(songId, currentPlayingSongId, 1)
                //musicSeekBarProgressLiveData.value = MusicSeekBarProgress(currentPlayingSongId, 0)
                currentPlayingSongId = songId
                updateSeekBar()
                updateDataHolder(songId, true, false)
                setSongCompleteListener(songId)
            }
        }
    }

    fun pauseSong() {
        playStatusLiveData.value = MusicPlayStausModel(currentPlayingSongId, currentPlayingSongId, 2)
        updateDataHolder(currentPlayingSongId, isPlaying = false, isPaused = true)
        mediaPlayer.pause()
    }

    fun getDuration(): Int {
        var duration = 0
        if (mediaPlayer.isPlaying) {
            duration = mediaPlayer.duration
        }
        return duration
    }

    fun seekTo(progress: Int) {
        mediaPlayer.seekTo(progress)
    }

    fun isPlaying(songID: Int): Boolean {
        var boolVal = false
        if (mediaPlayer.isPlaying && currentPlayingSongId == songID) {
            boolVal = true
        }
        return boolVal
    }

    @DelicateCoroutinesApi
    fun setSongCompleteListener(songID: Int) {
        mediaPlayer.setOnCompletionListener {
            var index = MyApplication.applicationContext().dataHoler.idList.indexOf(songID)
            if (index < MyApplication.applicationContext().dataHoler.idList.size - 1) {
                index++
            } else {
                index = 0
            }
            playSong(MyApplication.applicationContext().dataHoler.idList.get(index))
        }
    }

    fun updateDataHolder(id: Int, isPlaying: Boolean, isPaused: Boolean) {
        val aartiBean = MyApplication.applicationContext().dataHoler.hashMap[id]
        aartiBean?.isPlaying = isPlaying
        aartiBean?.isPaused = isPaused
        MyApplication.applicationContext().dataHoler.hashMap.put(id, aartiBean)
    }

    @DelicateCoroutinesApi
    fun updateSeekBar() {
        GlobalScope.launch {
            var currentPosition = mediaPlayer.currentPosition
            val total = mediaPlayer.duration
            while (mediaPlayer.isPlaying && currentPosition < total) {
                try {
                    delay(1000L)
                    currentPosition = mediaPlayer.currentPosition
                } catch (e: InterruptedException) {
                } catch (e: Exception) {
                }
                musicSeekBarProgressLiveData.postValue(MusicSeekBarProgress(currentPlayingSongId, currentPosition))
            }
        }
    }

}