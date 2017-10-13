package dog.poodle.control;

/**
 * Created by pk on 2017-10-13.
 */
public class OutputLayer {

    private final int _outputSize;
    private final int controllerSize;
    private final int memoryWidth;
    //private final int _headUnitSize;

    public OutputLayer(int outputSize, int controllerSize, int headCount, int memoryUnitSizeM){
        this._outputSize = outputSize;
        this.controllerSize = controllerSize;
        this.memoryWidth = memoryUnitSizeM;
        //this._headUnitSize = Head.getUnitSize(memoryUnitSizeM);
    }
}
