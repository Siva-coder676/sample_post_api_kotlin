data class PostApiResponse(
    val id: Int, // The API generates this automatically
    val title: String,
    val body: String,
    val userId: Int
)