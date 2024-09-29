package arvores;

public class ArvoreBinaria {

	private class Nodo{
		
		private int chave;
		private Nodo dir, esq;
		
		public Nodo(int item) {
			this.chave = item;
			dir = esq = null;
		}
		
	}
	
	private Nodo raiz;
	
	public ArvoreBinaria() {
		raiz = null;
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
}