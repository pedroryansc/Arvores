package arvoreRubroNegra;

public class Main {
	public static void main(String[] args) {
		
		ArvoreRubroNegra arvore = new ArvoreRubroNegra();
		
		arvore.contarVermelhosPretos();
		
		arvore.inserir(3);
		arvore.inserir(4);
		arvore.inserir(6);
		arvore.inserir(1);
		arvore.inserir(8);
		
		arvore.mostrarArvore();
		
		arvore.contarVermelhosPretos();
		
	}
}