package com.todoapp.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class TodoTest {

    @Test
    fun `create todo with valid title and description`() {
        val todo = Todo.create(
            title = "Complete project",
            description = "Finish the todo app implementation"
        )
        
        assertNotNull(todo)
        assertEquals("Complete project", todo.title)
        assertEquals("Finish the todo app implementation", todo.description)
        assertEquals(false, todo.isCompleted)
    }

    @Test
    fun `create todo with completed status`() {
        val todo = Todo.create(
            title = "Completed task",
            isCompleted = true
        )
        
        assertNotNull(todo)
        assertEquals(true, todo.isCompleted)
    }

    @Test
    fun `throw exception for empty title`() {
        assertThrows<IllegalArgumentException> {
            Todo.create(title = "")
        }
    }

    @Test
    fun `throw exception for blank title`() {
        assertThrows<IllegalArgumentException> {
            Todo.create(title = "   ")
        }
    }

    @Test
    fun `throw exception for title longer than 100 characters`() {
        val longTitle = "a".repeat(101)
        assertThrows<IllegalArgumentException> {
            Todo.create(title = longTitle)
        }
    }

    @Test
    fun `throw exception for description longer than 500 characters`() {
        val longDescription = "a".repeat(501)
        assertThrows<IllegalArgumentException> {
            Todo.create(
                title = "Valid Title", 
                description = longDescription
            )
        }
    }

    @Test
    fun `allow null description`() {
        val todo = Todo.create(
            title = "Task with no description"
        )
        
        assertNotNull(todo)
        assertEquals(null, todo.description)
    }
}