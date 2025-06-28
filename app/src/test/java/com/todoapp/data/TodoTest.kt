package com.todoapp.data

import kotlinx.serialization.json.Json
import org.junit.Assert.*
import org.junit.Test
import java.time.LocalDateTime

class TodoTest {

    @Test
    fun `create todo with valid properties`() {
        val todo = Todo(title = "Test Todo")
        
        assertEquals("Test Todo", todo.title)
        assertFalse(todo.isCompleted)
        assertNull(todo.description)
    }

    @Test
    fun `update todo properties`() {
        val originalTodo = Todo(title = "Original Todo")
        val updatedTodo = originalTodo.update(
            title = "Updated Todo",
            description = "New description",
            isCompleted = true
        )

        assertEquals("Updated Todo", updatedTodo.title)
        assertEquals("New description", updatedTodo.description)
        assertTrue(updatedTodo.isCompleted)
        assertNotEquals(originalTodo.updatedAt, updatedTodo.updatedAt)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `create todo with blank title fails`() {
        Todo(title = "")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `create todo with title exceeding max length fails`() {
        Todo(title = "A".repeat(101))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `create todo with description exceeding max length fails`() {
        Todo(
            title = "Valid Title",
            description = "A".repeat(501)
        )
    }

    @Test
    fun `todo can be serialized and deserialized`() {
        val originalTodo = Todo(
            id = 1,
            title = "Serialization Test",
            description = "Testing JSON serialization",
            isCompleted = true
        )

        // Note: In a real-world scenario, you'd use kotlinx.serialization
        // This is a placeholder to demonstrate serialization concept
        val json = Json.encodeToString(Todo.serializer(), originalTodo)
        val deserializedTodo = Json.decodeFromString(Todo.serializer(), json)

        assertEquals(originalTodo, deserializedTodo)
    }
}