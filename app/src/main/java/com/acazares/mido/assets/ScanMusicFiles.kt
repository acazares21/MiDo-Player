package com.acazares.mido.assets

import android.content.Context
import android.provider.MediaStore
import android.util.Log

private fun scanMusicFiles(context: Context) {
    val projection = arrayOf(
        MediaStore.Audio.Media._ID,
        MediaStore.Audio.Media.TITLE,
        MediaStore.Audio.Media.ARTIST,
        MediaStore.Audio.Media.ALBUM,
        MediaStore.Audio.Media.DURATION,
        MediaStore.Audio.Media.DATA
    )

    val selection = "${MediaStore.Audio.Media.IS_MUSIC} != 0"

    val sortOrder = "${MediaStore.Audio.Media.TITLE} ASC"

    val cursor = context.contentResolver.query(
        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
        projection,
        selection,
        null,
        sortOrder
    )

    val test = context.contentResolver.query(
        MediaStore.Audio.Media.INTERNAL_CONTENT_URI,
        projection, selection, null, sortOrder
    )

    cursor?.use {
        val idColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
        val titleColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
        val artistColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
        val albumColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)
        val durationColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
        val dataColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)

        while (it.moveToNext()) {
            val id = it.getLong(idColumn)
            val title = it.getString(titleColumn)
            val artist = it.getString(artistColumn)
            val album = it.getString(albumColumn)
            val duration = it.getLong(durationColumn)
            val data = it.getString(dataColumn)

            // Aquí puedes almacenar la información de cada archivo de música encontrado
            // en una lista o en una base de datos para su uso posterior

            // Ejemplo de uso de la información:
            Log.d(
                "MusicScanner",
                "ID: $id, Title: $title, Artist: $artist, Album: $album, Duration: $duration, Data: $data"
            )
        }
    }
}
