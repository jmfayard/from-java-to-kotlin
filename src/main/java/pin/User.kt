package pin

data class User(val id: Int, val name: String, val age: Int  = 42)

class UserRepository {
    fun fetchUsers() = listOf(
        User(1, "Jean-Michel", 39),
        User(2, "Patrick", 37)
    )
}
