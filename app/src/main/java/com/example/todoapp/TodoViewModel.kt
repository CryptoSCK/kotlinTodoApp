package com.example.todoapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TodoViewModel(private val repository: TodoRepository) : ViewModel() {
    // Sealed class to represent deletion state
    sealed class DeletionState {
        object Idle : DeletionState()
        object Success : DeletionState()
        data class Error(val message: String) : DeletionState()
    }

    // State flow to track deletion status
    private val _deletionState = MutableStateFlow<DeletionState>(DeletionState.Idle)
    val deletionState: StateFlow<DeletionState> = _deletionState.asStateFlow()

    /**
     * Deletes a todo item and provides feedback through deletionState
     * @param todoItem The todo item to be deleted
     */
    fun deleteTodoItem(todoItem: TodoItem) {
        viewModelScope.launch {
            try {
                repository.delete(todoItem)
                _deletionState.value = DeletionState.Success
            } catch (e: Exception) {
                _deletionState.value = DeletionState.Error(
                    "Failed to delete todo item: ${e.localizedMessage}"
                )
            } finally {
                // Reset state after handling
                _deletionState.value = DeletionState.Idle
            }
        }
    }
}