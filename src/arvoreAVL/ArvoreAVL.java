package arvoreAVL;

public class ArvoreAVL {

	private class Nodo{
		
		private int valor;
		private Nodo esquerdo, direito;
		private int alturaEsq, alturaDir;
		
		public Nodo(int valor) {
			this.valor = valor;
			esquerdo = direito = null;
			alturaEsq = alturaDir = 0;
		}
		
	}
	
	// Raiz da árvore AVL
	private Nodo raiz;
	
	// Construtor: inicializa uma árvore vazia
	public ArvoreAVL() {
		raiz = null;
	}
	
	public void inserir(int valor) {
		raiz = inserirNodo(raiz, valor);
	}
	
	private Nodo inserirNodo(Nodo atual, int valor) {
		if(atual == null)
			return new Nodo(valor);
		
		// Inserção na subárvore esquerda (valores menores)
		if(valor < atual.valor) {
			atual.esquerdo = inserirNodo(atual.esquerdo, valor);
			atual.alturaEsq = Math.max(atual.esquerdo.alturaEsq, atual.esquerdo.alturaDir) + 1;
		}
		// Inserção na subárvore direita (valores maiores)
		else if(valor > atual.valor) {
			atual.direito = inserirNodo(atual.direito, valor);
			atual.alturaDir = Math.max(atual.direito.alturaEsq, atual.direito.alturaDir) + 1;
		}
		
		// Após a inserção, verificar se a árvore precisa de balanceamento
		return balancear(atual);
	}
	
	private Nodo balancear(Nodo atual) {
		int fatorBalanceamento = atual.alturaDir - atual.alturaEsq;
		
		// Caso de desbalanceamento à direita (ou seja, fator positivo)
		if(fatorBalanceamento == 2) {
			// Se o filho à direita também está desbalanceado para a direita (ou seja, fator positivo)
			// Rotação simples (à esquerda)
			if(atual.direito.alturaDir >= atual.direito.alturaEsq)
				return rotacaoEsquerda(atual);
			else {
				// Caso contrário, rotação dupla à esquerda (ou direita-esquerda)
				atual.direito = rotacaoDireita(atual.direito);
				return rotacaoEsquerda(atual);
			}
		} else if(fatorBalanceamento == -2) {
			// Se o filho à esquerda está desbalanceado à esquerda
			// Rotação simples (à direita)
			if(atual.esquerdo.alturaEsq >= atual.esquerdo.alturaDir)
				return rotacaoDireita(atual);
			else {
				// Caso contrário, rotação dupla à direita (ou esquerda-direita)
				atual.esquerdo = rotacaoEsquerda(atual.esquerdo);
				return rotacaoDireita(atual);
			}
		}
		
		// Retorna o nó atual e, se necessário, balanceado
		return atual;
	}
	
	private Nodo rotacaoEsquerda(Nodo raiz) {
		Nodo novaRaiz = raiz.direito; // Filho direito se torna a nova raiz;
		raiz.direito = novaRaiz.esquerdo;
		novaRaiz.esquerdo = raiz;
		
		// Atualizar alturas
		atualizarAlturas(raiz);
		atualizarAlturas(novaRaiz);
		
		return novaRaiz;
	}
	
	private Nodo rotacaoDireita(Nodo raiz) {
		Nodo novaRaiz = raiz.esquerdo; // Filho esquerdo se torna a nova raiz;
		raiz.esquerdo = novaRaiz.direito;
		novaRaiz.direito = raiz;
		
		// Atualizar alturas
		atualizarAlturas(raiz);
		atualizarAlturas(novaRaiz);
		
		return novaRaiz;
	}
	
	private void atualizarAlturas(Nodo nodo) {
		if(nodo != null) {
			nodo.alturaEsq = (nodo.esquerdo == null) ? 0 : 
				Math.max(nodo.esquerdo.alturaEsq, nodo.esquerdo.alturaDir) + 1;
		
			nodo.alturaDir = (nodo.direito == null) ? 0 : 
				Math.max(nodo.direito.alturaEsq, nodo.direito.alturaDir) + 1;
		}
	}
	
	public void mostrar() {
		mostrar(raiz);
	}
	
	private void mostrar(Nodo raiz) {
		if(raiz != null) {
			mostrar(raiz.esquerdo);
			System.out.println(raiz.valor);
			mostrar(raiz.direito);
		}
	}
	
	public boolean buscaChave(int valor) {
		 return buscaChave(raiz, valor);
	}
	
	private boolean buscaChave(Nodo atual, int valor) {
		if(atual != null) {
			if(atual.valor == valor || buscaChave(atual.esquerdo, valor) || buscaChave(atual.direito, valor))
				return true;
		}
		
		return false;
	}
	
	public void remover(int valor) {
		raiz = remover(raiz, valor);
	}
	
	private Nodo remover(Nodo atual, int valor) {
		if(atual == null)
			return null;
		
		if(valor < atual.valor)
			atual.esquerdo = remover(atual.esquerdo, valor);
		else if(valor > atual.valor)
			atual.direito = remover(atual.direito, valor);
		else {
			if(atual.esquerdo == null)
				return atual.direito;
			else if(atual.direito == null)
				return atual.esquerdo;
			else {
				Nodo sucessor = encontrarSucessor(atual.direito);
				atual.valor = sucessor.valor;
				atual.direito = remover(atual.direito, sucessor.valor);
			}
		}
		
		return balancear(atual);
	}
	
	private Nodo encontrarSucessor(Nodo atual) {
		while(atual.esquerdo != null)
			atual = atual.esquerdo;
		
		return atual;
	}
	
}