const display = document.getElementById('display');
const previousOp = document.getElementById('previous-op');

let currentInput = '';
let isEvaluated = false;

function appendValue(value) {
  if (isEvaluated) {

    if (!isNaN(value) || value === '.') {
      currentInput = '';
    }
    isEvaluated = false;
  }

  if (display.value === '0' && value !== '.') {
    currentInput = value;
  } else {
    currentInput += value;
  }

  updateDisplay();
}

function appendFunction(func) {
  if (isEvaluated) {
    isEvaluated = false;
  }

  if (display.value === '0') {
    currentInput = func + '(';
  } else {
    currentInput += func + '(';
  }

  updateDisplay();
}

function clearDisplay() {
  currentInput = '';
  previousOp.innerText = '';
  display.value = '0';
  isEvaluated = false;
}

function deleteLast() {
  if (isEvaluated) {
    clearDisplay();
    return;
  }
  currentInput = currentInput.slice(0, -1);
  if (currentInput === '') {
    display.value = '0';
  } else {
    updateDisplay();
  }
}

function updateDisplay() {
  display.value = currentInput
    .replace(/Math\.sin/g, 'sin')
    .replace(/Math\.cos/g, 'cos')
    .replace(/Math\.tan/g, 'tan')
    .replace(/Math\.log10/g, 'log')
    .replace(/Math\.log/g, 'ln')
    .replace(/Math\.sqrt/g, '√')
    .replace(/Math\.PI/g, 'π')
    .replace(/Math\.E/g, 'e')
    .replace(/\*\*/g, '^')
    .replace(/\*/g, '×')
    .replace(/\//g, '÷');
}

function calculate() {
  if (!currentInput) return;

  try {
    previousOp.innerText = display.value;
    
    let expression = currentInput;
    
    let result = new Function(`return ${expression}`)();

    if (result === Infinity || isNaN(result)) {
      display.value = 'Erro';
    } else {
      result = Math.round(result * 1e10) / 1e10;
      display.value = result;
      currentInput = result.toString();
    }
    
    isEvaluated = true;
  } catch (error) {
    display.value = 'Erro de Sintaxe';
    isEvaluated = true;
  }
}

document.addEventListener('keydown', (event) => {
  const key = event.key;

  if (!isNaN(key) || key === '.') {
    appendValue(key);
  } else if (key === '+') {
    appendValue('+');
  } else if (key === '-') {
    appendValue('-');
  } else if (key === '*') {
    appendValue('*');
  } else if (key === '/') {
    appendValue('/');
  } else if (key === '(' || key === ')') {
    appendValue(key);
  } else if (key === 'Enter' || key === '=') {
    event.preventDefault();
    calculate();
  } else if (key === 'Backspace') {
    deleteLast();
  } else if (key === 'Escape') {
    clearDisplay();
  }
});