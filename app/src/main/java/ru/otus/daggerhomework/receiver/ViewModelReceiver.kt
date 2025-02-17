package ru.otus.daggerhomework.receiver

import android.app.Application
import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import ru.otus.daggerhomework.ApplicationContext
import ru.otus.daggerhomework.GetColorValue
import javax.inject.Inject

class ViewModelReceiver @Inject constructor(
    @ApplicationContext private val context: Context,
    private val stateFlow: GetColorValue
) {

    private val _colorFlow = MutableStateFlow<Int?>(null)
    val colorFlow: StateFlow<Int?> = _colorFlow

    suspend fun observeColors() {
        if (context !is Application) throw RuntimeException("Здесь нужен контекст апликейшена")
        Toast.makeText(context, "Color received", Toast.LENGTH_LONG).show()
        _colorFlow.emitAll(stateFlow.getValue())
    }
}