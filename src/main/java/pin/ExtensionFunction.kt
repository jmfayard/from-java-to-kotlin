package pin

fun formatUsers() {
    val formattedUser = formatUser(UserRepository().fetchUsers().first())
    println(formattedUser)
}

fun formatUser(user: User): String {
    return "User has id=${user.id} name=${user.name} and age=${user.age}"
}
