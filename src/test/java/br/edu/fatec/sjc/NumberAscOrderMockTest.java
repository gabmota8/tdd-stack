package br.edu.fatec.sjc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class NumberAscOrderMockTest {

    private CustomStack<Integer> mockStack;
    private NumberAscOrder<Integer> numberAscOrder;

    @BeforeEach
    public void setup() {
       
        mockStack = Mockito.mock(CustomStack.class);
        numberAscOrder = new NumberAscOrder<>(mockStack);
    }

    @Test
    public void testSortWithElements() throws StackEmptyException {
        
        when(mockStack.isEmpty())
            .thenReturn(false)  
            .thenReturn(false)  
            .thenReturn(true);  

        when(mockStack.pop())
            .thenReturn(30) 
            .thenReturn(10); 

       
        List<Integer> sortedList = numberAscOrder.sort();

        
        assertEquals(List.of(10, 30), sortedList, "A lista deve estar ordenada");

        
        verify(mockStack, times(3)).isEmpty(); 
        verify(mockStack, times(2)).pop();  
    }   

    @Test
    public void testSortWithEmptyStack() throws StackEmptyException {
        
        when(mockStack.isEmpty()).thenReturn(true);

        
        List<Integer> sortedList = numberAscOrder.sort();

        
        assertTrue(sortedList.isEmpty(), "A lista deve estar vazia se a pilha estiver vazia");

        
        verify(mockStack, atLeastOnce()).isEmpty();
        verify(mockStack, never()).pop(); 
    }

    @Test
    public void testSortWithNullElements() throws StackEmptyException {
        
        when(mockStack.isEmpty())
            .thenReturn(false)
            .thenReturn(false)
            .thenReturn(true);

        when(mockStack.pop())
            .thenReturn(null)  
            .thenReturn(20);  

        
        List<Integer> sortedList = numberAscOrder.sort();

        
        assertEquals(List.of(20), sortedList, "Elementos nulos devem ser ignorados");

        
        verify(mockStack, times(2)).pop();
    }
}