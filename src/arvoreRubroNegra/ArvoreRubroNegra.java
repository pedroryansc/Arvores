package arvoreRubroNegra;

public class ArvoreRubroNegra {

	// Enum para definir as cores dos nós
	private enum Cor{
		VERMELHO,
		PRETO
	}
	
	private class Nodo{
		
		private int valor;
		private Cor cor;
		private Nodo esquerdo, direito, pai;
		
		// Construtor do Nodo
		public Nodo(int valor){
			this.valor = valor;
			cor = Cor.VERMELHO; // Novos nodos são inseridos como vermelhos
			esquerdo = direito = pai = null;
		}
		
	}
	
	private Nodo raiz;
	
	// Construtor da árvore
	public ArvoreRubroNegra() {
		raiz = null;
	}
	
	public void inserir(int valor) {
		Nodo novoNodo = new Nodo(valor);
		raiz = inserirNodo(raiz, novoNodo);
			corrigirInsercao(novoNodo); // Corrige eventuais violações das propriedades da Árvore Rubro-Negra
	}
	
	private Nodo inserirNodo(Nodo atual, Nodo novoNodo) {
		// Caso base: se a posição for nula, insere o novo nó
		if(atual == null)
			return novoNodo;
		
		// Se o valor do novo nó for menor, insere à esquerda
		if(novoNodo.valor < atual.valor) {
			atual.esquerdo = inserirNodo(atual.esquerdo, novoNodo);
			atual.esquerdo.pai = atual;
		}
		// Se o valor do novo nó for maior, insere à direita
		else if(novoNodo.valor > atual.valor) {
			atual.direito = inserirNodo(atual.direito, novoNodo);
			atual.direito.pai = atual;
		}
		
		return atual;
	}
	
	private void corrigirInsercao(Nodo nodo) {
		Nodo pai, avo;
		
		// Enquanto o pai do nó for vermelho (e, portanto, houver violação)
		while(nodo != raiz && nodo.pai.cor == Cor.VERMELHO) {
			pai = nodo.pai;
			avo = pai.pai;
			
			// Caso 1: Pai do nó é filho esquerdo do avô
			if(pai == avo.esquerdo) {
				Nodo tio = avo.direito; // Tio do nó
				
				// Caso 1.1: O tio é vermelho (necessário recolorir)
				if(tio != null && tio.cor == Cor.VERMELHO) {
					pai.cor = Cor.PRETO; // Pai fica preto
					tio.cor = Cor.PRETO; // Tio fica preto
					avo.cor = Cor.VERMELHO; // Avô fica vermelho
					nodo = avo; // Continua verificando o avô
				}
				// Caso 1.2: O tio é preto ou nulo (necessário fazer rotações)
				else {
					// Caso 1.2.1: O nó é filho direito (rotação à esquerda)
					if(nodo == pai.direito) {
						rotacaoEsquerda(pai);
						nodo = pai;
						pai = nodo.pai;
					}
					// Caso 1.2.2: O nó é filho esquerdo (rotação à direita)
					rotacaoDireita(avo);
					Cor auxCor = pai.cor;
					pai.cor = avo.cor;
					avo.cor = auxCor;
					nodo = pai;
				}
			// Caso 2: Pai do nó é filho direito do avô
			} else {
				Nodo tio = avo.esquerdo;
				
				// Caso 2.1: O tio é vermelho (necessário recolorir)
				if(tio != null && tio.cor == Cor.VERMELHO) {
					pai.cor = Cor.PRETO;
					tio.cor = Cor.PRETO;
					avo.cor = Cor.VERMELHO;
					nodo = avo;
				}
				// Caso 2.2: O tio é preto ou nulo (necessário fazer rotações)
				else {
					// Caso 2.2.1: O nó é filho esquerdo (rotação à direita)
					if(nodo == pai.esquerdo) {
						rotacaoDireita(pai);
						nodo = pai;
						pai = nodo.pai;
					}
					// Caso 2.2.2: O nó é filho direito (rotação à esquerda)
					rotacaoEsquerda(avo);
					Cor auxCor = pai.cor;
					pai.cor = avo.cor;
					avo.cor = auxCor;
					nodo = pai;
				}
			}
		}
		
		raiz.cor = Cor.PRETO;
	}
	
	// Método para realizar uma rotação à esquerda
	private void rotacaoEsquerda(Nodo nodo) {
		Nodo novoNodo = nodo.direito;
		nodo.direito = novoNodo.esquerdo;
		
		if(novoNodo.esquerdo != null)
			novoNodo.esquerdo.pai = nodo;
		
		novoNodo.pai = nodo.pai;
		
		if(nodo.pai == null)
			raiz = novoNodo;
		else if(nodo == nodo.pai.esquerdo)
			nodo.pai.esquerdo = novoNodo;
		else
			nodo.pai.direito = novoNodo;
		
		novoNodo.esquerdo = nodo;
		nodo.pai = novoNodo;
	}
	
	// Método para realizar uma rotação à direita
	private void rotacaoDireita(Nodo nodo) {
		Nodo novoNodo = nodo.esquerdo;
		nodo.esquerdo = novoNodo.direito;
		
		if(novoNodo.direito != null)
			novoNodo.direito.pai = nodo;
		
		novoNodo.pai = nodo.pai;
		
		if(nodo.pai == null)
			raiz = novoNodo;
		else if(nodo.pai != null && nodo == nodo.pai.direito)
			nodo.pai.direito = novoNodo;
		else if(nodo.pai != null)
			nodo.pai.esquerdo = novoNodo;
		
		novoNodo.direito = nodo;
		nodo.pai = novoNodo;
	}
	
	// Método auxiliar para exibir a árvore
	public void mostrarArvore() {
		if(raiz == null)
			System.out.println("A árvore está vazia.");
		else
			mostrarArvoreRecursiva(raiz, "", true);
	}
	
	private void mostrarArvoreRecursiva(Nodo nodo, String prefixo, boolean isFilhoDireito) {
		if(nodo != null) {
			System.out.println(prefixo + (isFilhoDireito ? "D|--- " : "E|--- ") + nodo.valor + " (" + nodo.cor + ")");
			String novoPrefixo = prefixo + (isFilhoDireito ? " " : "|");
			mostrarArvoreRecursiva(nodo.esquerdo, novoPrefixo, false);
			mostrarArvoreRecursiva(nodo.direito, novoPrefixo, true);
		}
	}
	
	public void contarVermelhosPretos() {
		if(raiz == null)
			System.out.println("A árvore está vazia.");
		else {
			int[] quantNodos = new int[2];
			quantNodos = contarVermelhosPretos(raiz, quantNodos);
			
			System.out.println("Quantidade de nós vermelhos: " + quantNodos[0]);
			System.out.println("Quantidade de nós pretos: " + quantNodos[1]);
		}
	}
	
	private int[] contarVermelhosPretos(Nodo atual, int[] quantNodos) {
		if(atual != null) {
			if(atual.cor == Cor.VERMELHO)
				quantNodos[0]++;
			else
				quantNodos[1]++;
			contarVermelhosPretos(atual.esquerdo, quantNodos);
			contarVermelhosPretos(atual.direito, quantNodos);
		}
		
		return quantNodos;
	}
	
	public void mostrarOrdemCrescente() {
		mostrarCrescenteRecursivo(raiz);
	}
	
	private void mostrarCrescenteRecursivo(Nodo atual) {
		if(atual != null) {
			mostrarCrescenteRecursivo(atual.esquerdo);
			System.out.println(atual.valor);
			mostrarCrescenteRecursivo(atual.direito);
		}
	}
	
}