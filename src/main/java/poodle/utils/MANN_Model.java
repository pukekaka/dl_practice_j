package poodle.utils;

import org.tensorflow.*;

public class MANN_Model {

    private Output x_image;
    private Output x_label;
    private Output y;

    public MANN_Model(InitData data) {

        if (data.getLabel_type().equals("one_hot")){
            data.setOutput_dim(data.getN_classes());
        }
        else if(data.getLabel_type().equals("five_hot")){
            data.setOutput_dim(25);
        }

        try (Graph g = new Graph()) {
            this.x_image = g.opBuilder("Placeholder", "x_image")
                    .setAttr("dtype", DataType.FLOAT)
                    .setAttr("shape", Shape.make(data.getBatch_size(), data.getSeq_length(), data.getImage_width() * data.getImage_height()))
                    .build()
                    .output(0);
            this.x_label = g.opBuilder("Placeholder", "x_label")
                    .setAttr("dtype", DataType.FLOAT)
                    .setAttr("shape", Shape.make(data.getBatch_size(), data.getSeq_length(), data.getOutput_dim()))
                    .build()
                    .output(0);
            this.y = g.opBuilder("Placeholder", "y")
                    .setAttr("dtype", DataType.FLOAT)
                    .setAttr("shape", Shape.make(data.getBatch_size(), data.getSeq_length(), data.getOutput_dim()))
                    .build()
                    .output(0);

            if(data.getModel().equals("LSTM")){
                
            }

//
//            if args.model == 'LSTM':
//            def rnn_cell(rnn_size):
//            return tf.nn.rnn_cell.BasicLSTMCell(rnn_size)
//            cell = tf.nn.rnn_cell.MultiRNNCell([rnn_cell(args.rnn_size) for _ in range(args.rnn_num_layers)])


    }







}
