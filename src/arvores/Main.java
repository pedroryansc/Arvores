package arvores;

public class Main {
	public static void main(String[] args) {
		
		ArvoreBinaria arvore = new ArvoreBinaria();
		
		arvore.inserir(50);
		arvore.inserir(30);
		arvore.inserir(20);
		arvore.inserir(35);
		
		arvore.mostrarEmOrdemCrescente();
		
		System.out.println();
		
		arvore.mostrarEmOrdemDecrescente();
		
	}
}