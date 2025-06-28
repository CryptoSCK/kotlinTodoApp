package com.todoapp.model

import java.time.LocalDateTime
import java.util.UUID

/**
 * Represents a Todo item with comprehensive properties and validation.
 *
 * @property id Unique identifier for the Todo item
 * @property title Title of the Todo item (must not be blank)
 * @property description Optional description of the Todo item
 * @property isCompleted Current completion status of the Todo item
 * @property priority Priority level of the Todo item
 * @property createdAt Timestamp of Todo item creation
 * @property dueDate Optional due date for the Todo item
 */
data class Todo(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String? = null,
    val isCompleted: Boolean = false,
    val priority: Priority = Priority.MEDIUM,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val dueDate: LocalDateTime? = null
) {
    /**
     * Enum representing Todo item priority levels
     */
    enum class Priority {
        LOW, MEDIUM, HIGH
    }

    init {
        // Validation for title
        require(title.isNotBlank()) { "Todo title cannot be blank" }
        
        // Optional: Additional validation for due date
        dueDate?.let { 
            require(it.isAfter(createdAt)) { "Due date must be after creation date" }
        }
    }

    /**
     * Creates a copy of the Todo item with updated completion status
     */
    fun toggleCompletion(): Todo = copy(isCompleted = !isCompleted)
}