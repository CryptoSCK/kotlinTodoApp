package com.todoapp.model

data class Todo(
    val id: Long? = null,
    val title: String,
    val description: String? = null,
    val isCompleted: Boolean = false
) {
    init {
        validateTitle(title)
        validateDescription(description)
    }

    private fun validateTitle(title: String) {
        require(title.isNotBlank()) { "Title cannot be empty" }
        require(title.length <= 100) { "Title must be 100 characters or less" }
    }

    private fun validateDescription(description: String?) {
        description?.let { desc ->
            require(desc.length <= 500) { "Description must be 500 characters or less" }
        }
    }

    companion object {
        fun create(
            title: String,
            description: String? = null,
            isCompleted: Boolean = false
        ): Todo {
            return Todo(
                title = title,
                description = description,
                isCompleted = isCompleted
            )
        }
    }
}