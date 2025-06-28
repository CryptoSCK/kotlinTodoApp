package com.todoapp.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime

class TodoTest {

    @Test
    fun `create todo with valid title`() {
        val todo = Todo.create("Buy groceries")
        
        assertNotNull(todo.id)
        assertEquals("Buy groceries", todo.title)
        assertFalse(todo.isCompleted)
        assertNull(todo.description)
        assertNotNull(todo.createdAt)
    }

    @Test
    fun `create todo with title and description`() {
        val todo = Todo.create("Buy groceries", "Milk, eggs, bread")
        
        assertNotNull(todo.id)
        assertEquals("Buy groceries", todo.title)
        assertEquals("Milk, eggs, bread", todo.description)
    }

    @Test
    fun `throw exception for empty title`() {
        val exception = assertThrows<IllegalArgumentException> {
            Todo.create("")
        }
        assertEquals("Title cannot be empty", exception.message)
    }

    @Test
    fun `throw exception for title exceeding max length`() {
        val longTitle = "A".repeat(101)
        val exception = assertThrows<IllegalArgumentException> {
            Todo.create(longTitle)
        }
        assertEquals("Title cannot exceed 100 characters", exception.message)
    }

    @Test
    fun `throw exception for description exceeding max length`() {
        val longDescription = "A".repeat(501)
        val exception = assertThrows<IllegalArgumentException> {
            Todo.create("Valid Title", longDescription)
        }
        assertEquals("Description cannot exceed 500 characters", exception.message)
    }

    @Test
    fun `update completion status`() {
        val todo = Todo.create("Test Task")
        val updatedTodo = todo.updateCompletionStatus(true)
        
        assertTrue(updatedTodo.isCompleted)
        assertNotSame(todo, updatedTodo)
    }

    @Test
    fun `reconstruct todo item`() {
        val id = "test-id-123"
        val createdAt = LocalDateTime.now()
        val todo = Todo.reconstruct(
            id = id,
            title = "Existing Task", 
            description = "Some description",
            isCompleted = true,
            createdAt = createdAt
        )
        
        assertEquals(id, todo.id)
        assertEquals("Existing Task", todo.title)
        assertEquals("Some description", todo.description)
        assertTrue(todo.isCompleted)
        assertEquals(createdAt, todo.createdAt)
    }
}