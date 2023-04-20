import java.util.ArrayList;

/**
 * @author
 */
public class MochilaAV extends Mochila {

    public SolucionMochila resolver(ProblemaMochila pm) {
        //Duplicamos para que se muestre correctamente (evitamos modificar el objeto)
        ArrayList<Item> items = (ArrayList<Item>) pm.items.clone();

        // Sort usando expresión Lambda
        items.sort((i1, i2) -> {
            double densidad1 = (double) i1.valor / i1.peso;
            double densidad2 = (double) i2.valor / i2.peso;
            double dif = densidad1 - densidad2;
            if (dif > 0) {
                return -1;
            } else if (dif < 0) {
                return 1;
            } else {
                return i2.valor > i1.valor ? 1 : -1;
            }
        });

        int pesoRestante = pm.pesoMaximo;
        int valorTotal = 0;

        int[] valores = new int[items.size()];

        // Bucle simple insertando los elementos
        for (Item item : items) {
            while (valores[item.index] < item.unidades && item.peso <= pesoRestante) {
                valores[item.index]++;
                pesoRestante -= item.peso;
                valorTotal += item.valor;
            }
        }
        return new SolucionMochila(valores, pm.pesoMaximo - pesoRestante, valorTotal);
    }
}
