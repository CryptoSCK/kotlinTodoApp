package com.todoapp.model

import java.time.LocalDateTime
import java.util.UUID

/**
 * Represents a Todo item with validation logic
 * @property id Unique identifier for the todo item
 * @property title Title of the todo item
 * @property description Optional description of the todo item
 * @property isCompleted Completion status of the todo item
 * @property createdAt Timestamp of todo item creation
 */
data class Todo private constructor(
    val id: String,
    val title: String,
    val description: String? = null,
    val isCompleted: Boolean = false,
    val createdAt: LocalDateTime = LocalDateTime.now()
) {
    companion object {
        /**
         * Factory method to create a Todo item with built-in validation
         * @param title Todo item title (required, non-empty)
         * @param description Optional description
         * @return A validated Todo item
         * @throws IllegalArgumentException for invalid inputs
         */
        fun create(
            title: String, 
            description: String? = null
        ): Todo {
            // Validate title
            require(title.isNotBlank()) { "Title cannot be empty" }
            require(title.length <= 100) { "Title cannot exceed 100 characters" }

            // Validate description if provided
            description?.let {
                require(it.length <= 500) { "Description cannot exceed 500 characters" }
            }

            // Generate unique ID
            val id = UUID.randomUUID().toString()

            // Create and return Todo item
            return Todo(
                id = id,
                title = title.trim(),
                description = description?.trim(),
                isCompleted = false
            )
        }

        /**
         * Reconstruct a Todo item (useful for database retrieval)
         * @param id Existing todo item ID
         * @param title Todo item title
         * @param description Optional description
         * @param isCompleted Completion status
         * @param createdAt Creation timestamp
         * @return A Todo item
         */
        fun reconstruct(
            id: String,
            title: String,
            description: String? = null,
            isCompleted: Boolean = false,
            createdAt: LocalDateTime = LocalDateTime.now()
        ): Todo {
            require(id.isNotBlank()) { "ID cannot be empty" }
            require(title.isNotBlank()) { "Title cannot be empty" }

            return Todo(
                id = id,
                title = title.trim(),
                description = description?.trim(),
                isCompleted = isCompleted,
                createdAt = createdAt
            )
        }
    }

    /**
     * Create a copy of the Todo item with updated completion status
     * @param completed New completion status
     * @return A new Todo item with updated status
     */
    fun updateCompletionStatus(completed: Boolean): Todo {
        return this.copy(isCompleted = completed)
    }
}