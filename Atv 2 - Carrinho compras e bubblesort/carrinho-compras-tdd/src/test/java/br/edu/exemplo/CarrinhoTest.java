package br.edu.exemplo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CarrinhoTest {

    private static final double DELTA = 0.001;

    @Test
    void carrinhoNovoDeveTerTotalZero() {
        assertEquals(0.0, new Carrinho().getTotal(), DELTA);
    }

    @Test
    void deveSomarPrecoDeUmProdutoAdicionado() {
        Carrinho carrinho = new Carrinho();
        carrinho.adicionar(new Produto("Teclado", 100.0));
        assertEquals(100.0, carrinho.getTotal(), DELTA);
    }

    @Test
    void deveSomarPrecosDeVariosProdutos() {
        Carrinho carrinho = new Carrinho();
        carrinho.adicionar(new Produto("Teclado", 100.0));
        carrinho.adicionar(new Produto("Mouse", 50.0));
        assertEquals(150.0, carrinho.getTotal(), DELTA);
    }

    @Test
    void deveRecalcularTotalAoRemoverProduto() {
        Carrinho carrinho = new Carrinho();
        Produto teclado = new Produto("Teclado", 100.0);
        Produto mouse = new Produto("Mouse", 50.0);
        carrinho.adicionar(teclado);
        carrinho.adicionar(mouse);

        carrinho.remover(mouse);

        assertEquals(100.0, carrinho.getTotal(), DELTA);
    }

    @Test
    void naoDevePermitirProdutoComPrecoNegativo() {
        assertThrows(IllegalArgumentException.class,
                () -> new Produto("Teclado", -100.0));
    }

    @Test
    void naoDeveAplicarDescontoAbaixoDeQuinhentos() {
        Carrinho carrinho = new Carrinho();
        carrinho.adicionar(new Produto("Item", 499.99));
        assertEquals(499.99, carrinho.getTotal(), DELTA);
    }

    @Test
    void deveAplicarDescontoExatamenteEmQuinhentos() {
        Carrinho carrinho = new Carrinho();
        carrinho.adicionar(new Produto("Item", 500.00));
        assertEquals(450.00, carrinho.getTotal(), DELTA);
    }

    @Test
    void deveAplicarDescontoAcimaDeQuinhentos() {
        Carrinho carrinho = new Carrinho();
        carrinho.adicionar(new Produto("Item", 500.01));
        assertEquals(450.009, carrinho.getTotal(), DELTA);
    }

    @Test
    void deveAplicarDezPorCentoEmCompraDeSeiscentos() {
        Carrinho carrinho = new Carrinho();
        carrinho.adicionar(new Produto("Item", 600.00));
        assertEquals(540.00, carrinho.getTotal(), DELTA);
    }
}