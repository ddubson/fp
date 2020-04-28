// Simple function composition
const g = n => n + 1
const f = n => n * 2
const doStuffBetter = x => f(g(x))

console.log(doStuffBetter(20))

// A definition of a pipe
const pipe = (...fns) => x => fns.reduce((y, f) => f(y), x)

const output = pipe(g, f)(20)
console.log(output)

// Flip method - currying
const trace = value => label => {
  console.log(`${label}: ${value}`)
  return value;
}

const flip = fn => a => b => fn(b)(a)
const flippedTrace = flip(trace)

const pipeline = pipe(
  g,
  flippedTrace("after g"),
  f,
  flippedTrace("after f")
)

pipeline(20)
