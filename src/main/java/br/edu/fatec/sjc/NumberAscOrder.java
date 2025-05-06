package br.edu.fatec.sjc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class NumberAscOrder<T extends Number & Comparable<T>> {  
    private final CustomStack<T> stack;

    public NumberAscOrder(CustomStack<T> stack) {
        this.stack = stack;
    }

    public List<T> sort() {
        List<T> sortedList = new ArrayList<>();
        
        while (!stack.isEmpty()) {
            try {
                T value = stack.pop();
                if (value != null) { 
                    sortedList.add(value);
                }
            } catch (StackEmptyException e) {
                System.err.println("Erro ao remover elemento da pilha: " + e.getMessage());
            }
        }

        Collections.sort(sortedList);  
        return sortedList;
    }
}