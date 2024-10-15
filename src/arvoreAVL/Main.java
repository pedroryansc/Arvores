package arvoreAVL;

public class Main {
	public static void main(String[] args) {
		
		ArvoreAVL avl = new ArvoreAVL();
		
		avl.inserir(20);
		avl.inserir(15);
		avl.inserir(10);
		avl.inserir(30);
		avl.inserir(40);
		avl.inserir(5);
		
		avl.mostrar();
		
		System.out.println("\n" + avl.buscaChave(42) + "\n");
		
		avl.remover(10);
		avl.remover(5);
		
		avl.mostrar();
		
	}
}