package com.semana4.onepiecesearch.menu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {

    @Test
    public void entradaDoUserNaoPodeSerVazia() {
        Menu menu = new Menu(null, null, null, null);

        Integer resultado = menu.processInput("");

        assertNull(resultado);
    }

    @Test
    public void deveRetornarNumeroValido(){
        Menu menu = new Menu(null, null, null, null);

        Integer resultado = menu.processInput("2");

        assertEquals("2", resultado.toString());
    }


}