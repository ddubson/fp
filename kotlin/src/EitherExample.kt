import arrow.core.Option
import arrow.core.Some
import arrow.core.Try
import fp.option.Item

fun getItem(): Option<Item> {
    return Some(Item(1, "Hi"))
}

val itemTry = Try { getItem() }