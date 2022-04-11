package br.ce.wcaquino.servicos;

import br.ce.wcaquino.exceptions.NaoDeveDividirPorZeroException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculadoraTest {

    private Calculadora calc;

    @Before
    public void setup(){
        calc = new Calculadora();
    }

    @Test
    public void deveSomarDoisValores(){
        // Cenario
        int a = 5;
        int b = 3;

        // A��o
        int resultado = calc.somar(a, b);

        // Verifica��o
        Assert.assertEquals(8, resultado);
    }

    @Test
    public void deveSubtrairDoisValores(){
        // Cenario
        int a = 8;
        int b = 5;

        // A��o
        int resultado = calc.subtrair(a, b);

        //Verifica��o
        Assert.assertEquals(3, resultado);
    }

    @Test
    public void deveDividirDoisValores() throws NaoDeveDividirPorZeroException {
        // Cenario
        int a = 6;
        int b = 3;

        // A��o
        int resultado = calc.divide(a, b);

        // Verifica��o
        Assert.assertEquals(2, resultado);
    }

    @Test(expected = NaoDeveDividirPorZeroException.class)
    public void deveLancarExcecaoAoDividirPorZero() throws NaoDeveDividirPorZeroException {
        int a = 10;
        int b = 0;

        calc.divide(a, b);
    }
}
