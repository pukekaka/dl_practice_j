package dogtest.ntmtest.control;

/**
 * Created by pk on 2017-10-13.
 */
public class FeedForwardController {
    public HiddenLayer hidden;
    public OutputLayer output;

    public FeedForwardController(int controllerSize,
                                 int inputSize,
                                 int outputSize,
                                 int headCount,
                                 int memoryUnitSizeM) {

        this (new HiddenLayer (controllerSize, inputSize, headCount, memoryUnitSizeM),
                new OutputLayer(outputSize, controllerSize, headCount, memoryUnitSizeM));

    }

    private FeedForwardController(HiddenLayer hidden, OutputLayer output) {
        this.hidden = hidden;
        this.output = output;
    }

    /*
    public int inputSize() {
        return hidden.inputs();
    }
    */
}
