package arvores;

public class ArvoreBinaria {

	private class Nodo{
		
		private int chave;
		private Nodo dir, esq;
		
		public Nodo(int item) {
			this.chave = item;
			dir = esq = null;
		}

		public int getChave() {
			return chave;
		}

		public void setChave(int chave) {
			this.chave = chave;
		}

		public Nodo getDir() {
			return dir;
		}

		public void setDir(Nodo dir) {
			this.dir = dir;
		}

		public Nodo getEsq() {
			return esq;
		}

		public void setEsq(Nodo esq) {
			this.esq = esq;
		}
		
	}
	
	private Nodo raiz;
	
	public ArvoreBinaria() {
		raiz = null;
	}
	
	public Nodo getRaiz() {
		return raiz;
	}

	public void setRaiz(Nodo raiz) {
		this.raiz = raiz;
	}

	public void inserir(int chave) {
		raiz = inserirDado(raiz, chave);
	}
	
	private Nodo inserirDado(Nodo raiz, int chave) {
		if(raiz == null) {
			raiz = new Nodo(chave);
			return raiz;
		}
		if(chave < raiz.chave)
			raiz.esq = inserirDado(raiz.esq, chave);
		else if(chave > raiz.chave)
			raiz.dir = inserirDado(raiz.dir, chave);
		
		return raiz;
	}
	
	public void mostrarEmOrdemCrescente() {
		mostrarCrescente(raiz);
	}
	
	private void mostrarCrescente(Nodo raiz) {
		if(raiz != null) {
			mostrarCrescente(raiz.esq);
			System.out.println(raiz.chave);
			mostrarCrescente(raiz.dir);
		}
	}
	
	public void mostrarEmOrdemDecrescente() {
		mostrarDecrescente(raiz);
	}
	
	private void mostrarDecrescente(Nodo raiz) {
		if(raiz != null) {
			mostrarDecrescente(raiz.dir);
			System.out.println(raiz.chave);
			mostrarDecrescente(raiz.esq);
		}
	}
	
	public void remover(int chave) {
		setRaiz(removerNodo(getRaiz(), chave));
	}
	
	private Nodo removerNodo(Nodo raiz, int chave) {
		if(raiz == null)
			return null;
		
		if(chave < raiz.getChave())
			raiz.setEsq(removerNodo(raiz.getEsq(), chave));
		else if(chave > raiz.getChave())
			raiz.setDir(removerNodo(raiz.getDir(), chave));
		else {
			if(raiz.getEsq() == null)
				return raiz.getDir();
			else if(raiz.getDir() == null)
				return raiz.getEsq();
			else {
				Nodo sucessor = encontrarSucessor(raiz.getDir());
				raiz.setChave(sucessor.getChave());
				raiz.setDir(removerNodo(raiz.getDir(), sucessor.getChave()));
			}
		}
		
		return raiz;
	}
	
	private Nodo encontrarSucessor(Nodo nodo) {
		while(nodo.getEsq() != null)
			nodo = nodo.getEsq();
		
		return nodo;
	}
	
	public int maiorChave() {
		Nodo aux = raiz;
		while(aux.getDir() != null)
			aux = aux.getDir();
		
		return aux.getChave();
	}
	
	public void mostrarFolhas() {
		procurarFolhas(raiz);
	}
	
	private void procurarFolhas(Nodo raiz) {
		if(raiz != null) {
			procurarFolhas(raiz.getEsq());
			if(raiz.getEsq() == null && raiz.getDir() == null)
				System.out.println(raiz.getChave());
			procurarFolhas(raiz.getDir());
		}
	}
	
	public void mostrarNumerosPares() {
		procurarPares(raiz);
	}
	
	private void procurarPares(Nodo raiz) {
		if(raiz != null) {
			procurarPares(raiz.getEsq());
			if(raiz.getChave() % 2 == 0)
				System.out.println(raiz.getChave());
			procurarPares(raiz.getDir());
		}
	}
	
	public int tamanho() {
		return contarNodos(raiz, 0);
	}
	
	private int contarNodos(Nodo raiz, int cont) {
		if(raiz != null) {
			cont = contarNodos(raiz.getEsq(), cont);
			cont++;
			cont = contarNodos(raiz.getDir(), cont);
		}
		
		return cont;
	}
	
	public int altura() {
		return calcularAltura(raiz);
	}
	
	private int calcularAltura(Nodo nodo) {
		if(nodo == null)
			return 0; // Se o nodo for nulo, a altura é 0
		else {
			// Calcula a altura das subárvores esquerda e direita
			int alturaEsq = calcularAltura(nodo.getEsq());
			int alturaDir = calcularAltura(nodo.getDir());
			
			// Retorna o maior valor entre as alturas das subárvores mais 1 (contendo a raiz)
			return Math.max(alturaEsq, alturaDir) + 1;
		}
	}
	
}