package pin

fun strings() {
    val user = User(1, "Cedric", 23)

    val message = "User has id=" + user.id + " name=" + user.name + " and age=" + user.age

    val sqlQuerw = "\n" +
            " SELECT * from user \n" +
            " WHERE country = FR \n" +
            " ORDER BY id DESC"
}