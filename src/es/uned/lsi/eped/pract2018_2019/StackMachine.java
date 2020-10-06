package es.uned.lsi.eped.pract2018_2019;

import es.uned.lsi.eped.DataStructures.BTreeIF;
import es.uned.lsi.eped.DataStructures.Stack;
import es.uned.lsi.eped.pract2018_2019.Operator.OperatorType;

public class StackMachine {

	private Stack pila;
	private Operator nodoOperacion;
	private Operand nodoNumero;
	
	public StackMachine() {
		pila = new Stack();
		nodoOperacion = null;
		nodoNumero = null;
	}
	
	public Operand execute(SynTree syn) {

		BTreeIF<Node> nodoRaiz = syn.getSynTree();
		traversePostOrder(nodoRaiz);
		Operand resultado = (Operand) pila.getTop();
		return (resultado);
	}
	
	private void traversePostOrder(BTreeIF<Node> nodo) {
	    if (nodo != null) {
	        traversePostOrder(nodo.getRightChild());
	        traversePostOrder(nodo.getLeftChild());
	        imprimirSimbolo(nodo);
	        usarPila(nodo);
	    }
	}
	
	private void imprimirSimbolo(BTreeIF<Node> nodo) {
		if (nodo==null) {
			System.out.println("Nodo nulo");
			return;
		}
		if (nodo.getRoot() instanceof Operator) {
			nodoOperacion = (Operator) nodo.getRoot(); 
			//System.out.println(nodoOperacion.getOperatorType().name());
		} else if (nodo.getRoot() instanceof Operand) {
			nodoNumero = (Operand) nodo.getRoot(); 
			//System.out.println(nodoNumero.toString());
		} else {	
		    System.out.println("Fallo en el nodo");
		}
	}
	
	private void usarPila(BTreeIF<Node> nodo) {
		if (nodo==null) {
			System.out.println("Nodo nulo");
			return;
		}
		if (nodo.getRoot() instanceof Operator) {
			Operand Op1 = (Operand) pila.getTop();
			pila.pop();
			Operand Op2 = (Operand) pila.getTop();
			pila.pop();
			if (nodoOperacion.getOperatorType().equals(OperatorType.ADD)) {
				Op1.add(Op2);
			} else if (nodoOperacion.getOperatorType().equals(OperatorType.SUB)) {
				Op1.sub(Op2);
			} else if (nodoOperacion.getOperatorType().equals(OperatorType.MULT)) {
				Op1.mult(Op2);
			}
			pila.push(Op1);
		} else if (nodo.getRoot() instanceof Operand) {
			nodoNumero = (Operand) nodo.getRoot();
			pila.push(nodoNumero);
		} else {	
		    System.out.println("Fallo en el nodo");
		}
	}
}

