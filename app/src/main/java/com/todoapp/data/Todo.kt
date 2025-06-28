package com.todoapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

/**
 * Represents a Todo item with serialization and Room database support.
 *
 * @property id Unique identifier for the todo item
 * @property title Title of the todo item
 * @property description Optional description of the todo item
 * @property isCompleted Indicates whether the todo item is completed
 * @property createdAt Timestamp of when the todo item was created
 * @property updatedAt Timestamp of when the todo item was last updated
 */
@Serializable
@Entity(tableName = "todos")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String? = null,

    @ColumnInfo(name = "is_completed")
    val isCompleted: Boolean = false,

    @ColumnInfo(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @ColumnInfo(name = "updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now()
) {
    /**
     * Validates the todo item's properties.
     *
     * @throws IllegalArgumentException if validation fails
     */
    init {
        require(title.isNotBlank()) { "Todo title cannot be blank" }
        require(title.length <= 100) { "Todo title cannot exceed 100 characters" }
        description?.let {
            require(it.length <= 500) { "Todo description cannot exceed 500 characters" }
        }
    }

    /**
     * Creates a copy of the Todo item with updated properties.
     *
     * @param title New title (optional)
     * @param description New description (optional)
     * @param isCompleted New completion status (optional)
     * @return A new Todo item with updated properties
     */
    fun update(
        title: String? = null,
        description: String? = null,
        isCompleted: Boolean? = null
    ): Todo = copy(
        title = title ?: this.title,
        description = description ?: this.description,
        isCompleted = isCompleted ?: this.isCompleted,
        updatedAt = LocalDateTime.now()
    )
}