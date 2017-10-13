package dog.poodle.control;

/**
 * Created by pk on 2017-10-13.
 */
public class HiddenLayer {
    public final int inputs;
    public final int heads;
    public final int memoryUnitSizeM;

    public HiddenLayer(int controllerSize, int inputSize, int headCount, int memoryUnitSizeM){
        this.inputs = inputSize;
        this.heads = headCount;
        this.memoryUnitSizeM = memoryUnitSizeM;



    }
}
