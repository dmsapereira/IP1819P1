
public class IteratorInt {

    private int countNumbers,currNumber;
    private int [] numbers;

    /* Cria um iterador para o vetor de inteiros dados
     * @param: numbers - vetor de inteiros a iterar
     * @pre: numbers todo preenchido
     */
    public IteratorInt(int[] numbers) {
        countNumbers=numbers.length;
        currNumber=0;
        this.numbers=numbers;
    }
    /* Cria um iterador para o vetor de inteiros dados
     * @param: numbers - vetor de inteiros a iterar
     * @param: nElems - numero de elementos no vetor
     * @pre: nElems >0 && nElems <= numbers.length
     */
    public IteratorInt(int[] numbers, int nElems) {
        countNumbers=nElems;
        currNumber=0;
        this.numbers=numbers;
    }
    /* Indica se ha mais inteiros para iterar
     */
    public boolean hasNextInt() {
        return currNumber < countNumbers;
    }
    /* Retorna o proximo inteiro
     * @pre: hasNextInt()
     */
    public int nextInt() {
        return numbers[currNumber++];
    }

    /* Reinicia a iteracao
     */
    public void reinitialize() {
        currNumber=0;
    }

}
