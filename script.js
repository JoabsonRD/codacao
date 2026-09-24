function somar() {
    const n1 = parseFloat(document.getElementById('num1').value) || 0;
    
    const n2 = parseFloat(document.getElementById('num2').value) || 0;
    
    const soma = n1 + n2;
    
    document.getElementById('resultado').value = soma;
}