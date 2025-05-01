// Array to store cached factorial values for efficient lookups
const facTable = [1, 1];  // 0! = 1 and 1! = 1

const factorial = (n) => {
  // Return the cached value
  if (facTable[n] !== undefined) {
    return facTable[n];
  }
  
  facTable[n] = n * factorial(n - 1);
  return facTable[n];
};

onmessage = (e) => {
  const number = e.data;
  const factorialTable = [];

  for (let i = 1; i <= number; i++) {
    const fact = factorial(i);
    factorialTable.push({ number: i, factorial: fact });
  }

  postMessage(factorialTable);
};
