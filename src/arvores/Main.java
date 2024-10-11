package arvores;

public class Main {
	public static void main(String[] args) {
		
		ArvoreBinaria arvore = new ArvoreBinaria();
		
		arvore.inserir(50);
		arvore.inserir(30);
		arvore.inserir(20);
		arvore.inserir(35);
		arvore.inserir(60);
		arvore.inserir(55);
		arvore.inserir(25);
		
		arvore.mostrarEmOrdemCrescente();
		
		System.out.println();
		arvore.remover(30);
		
		arvore.mostrarEmOrdemDecrescente();
		
		System.out.println();
		
		System.out.println(arvore.maiorChave());
		
		System.out.println();
		
		arvore.mostrarFolhas();
		
		System.out.println();
		
		arvore.mostrarNumerosPares();
		
		System.out.println();
		
		System.out.println(arvore.tamanho());
		
		System.out.println();
		
		System.out.println(arvore.altura());
		
	}
}