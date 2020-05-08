import arrow.core.Option
import arrow.core.getOrElse

data class Item(val id: Int, val name: String)

interface FetchItemByIdQuery {
    fun fetch(): Option<Item>
}

class ConcreteFetchItemByIdQuery: FetchItemByIdQuery {
    override fun fetch(): Option<Item> {
        return Option(Item(1, "hey"))
    }
}

fun main() {
    val fetchItemByIdQuery = ConcreteFetchItemByIdQuery()
    val optionOfItem: Option<Item> = fetchItemByIdQuery.fetch()

    optionOfItem.getOrElse { "item" }
}