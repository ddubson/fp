const shoppingCart = [];

// impure addToCart
const addToCartImpure = (item) => {
  shoppingCart.push(item);
  return item;
}

console.log(JSON.stringify(addToCartImpure("Item 1")))

// pure addToCart
const addToCart = (item) => {
  return {
    ...shoppingCart,
    item
  }
}

console.log(JSON.stringify(addToCartImpure("Item 2")))
