package br.edu.fatec.sjc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class NumberAscOrderTest {

    private CustomStack<Integer> stack;
    private NumberAscOrder<Integer> numberAscOrder;

    @BeforeEach
    public void setup() {
        
        CalculableStrategy<Integer> identityStrategy = value -> {
            if (value == null) throw new NullPointerException("Valor não pode ser null");
            return value;
        };

        stack = new CustomStack<>(6, identityStrategy);
        numberAscOrder = new NumberAscOrder<>(stack);
    }

    @Test
    public void testSortWithRandomNumbers() {
        Random random = new Random();
        List<Integer> inserted = new ArrayList<>();

        try {
            for (int i = 0; i < 6; i++) {
                int num = random.nextInt(60); 
                inserted.add(num);
                stack.push(num);
            }
        } catch (StackFullException e) {
            fail("Erro inesperado ao adicionar elementos: " + e.getMessage());
        }

        List<Integer> sorted = numberAscOrder.sort();

        List<Integer> expected = new ArrayList<>(inserted);
        Collections.sort(expected);

        assertEquals(expected, sorted, "A lista ordenada não corresponde ao esperado");
    }

    @Test
    public void testSortWithEmptyStack() {
        List<Integer> sorted = numberAscOrder.sort();
        assertTrue(sorted.isEmpty(), "A lista deve estar vazia ao ordenar uma pilha vazia");
    }
}
