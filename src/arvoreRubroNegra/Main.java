package arvoreRubroNegra;

public class Main {
	public static void main(String[] args) {
		
		ArvoreRubroNegra arvore = new ArvoreRubroNegra();
		
		arvore.contarVermelhosPretos();
		
		arvore.inserir(360);
		arvore.inserir(940);
		arvore.inserir(1);
		arvore.inserir(8);
		arvore.inserir(4);
		arvore.inserir(513);
		arvore.inserir(6);
		arvore.inserir(143);
		arvore.inserir(364);
		arvore.inserir(815);
		arvore.inserir(955);
		arvore.inserir(84);
		arvore.inserir(300);
		arvore.inserir(638);
		arvore.inserir(3);
		
		arvore.mostrarArvore();
		
		arvore.contarVermelhosPretos();
		
		arvore.mostrarOrdemCrescente();
		
	}
}